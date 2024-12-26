package viewModel

import Data.AnimalResultItem
import Data.CallBackResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import repository.ApiRepository
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    private val _zooAnimalDataList = MutableStateFlow<List<AnimalResultItem>>(emptyList())
    val zooAnimalDataList: SharedFlow<List<AnimalResultItem>> = _zooAnimalDataList.asStateFlow()

    init {
        getData1()
    }

    val imageLoader = repository.getImageLoader()

    private fun getData1(){
        viewModelScope.launch {
            val result = repository.getZooData()
            if (result is CallBackResource.Success){
                _zooAnimalDataList.tryEmit(result.data.result?.results?: emptyList())
            }else{
                _zooAnimalDataList.tryEmit(emptyList())
            }
        }
    }
}