package viewModel

import Data.CallBackResource
import Data.ZooResultItem
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
class HomeViewModel @Inject constructor(
    private val repository: ApiRepository,
): ViewModel() {

    init {
        getData()
    }

    val imageLoader = repository.getImageLoader()

    private val _zooLibraryData = MutableStateFlow<List<ZooResultItem>>(emptyList())
    val zooLibraryData: SharedFlow<List<ZooResultItem>> = _zooLibraryData.asStateFlow()

    private fun getData(){
        viewModelScope.launch {
            repository.networkCall().let {
                if (it is CallBackResource.Success){
                    _zooLibraryData.tryEmit(it.data.result?.results?: emptyList())
                }else{_zooLibraryData.tryEmit(emptyList())}
            }
        }
    }
}