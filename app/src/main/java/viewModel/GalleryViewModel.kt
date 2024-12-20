package viewModel

import Data.AnimalResultItem
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import remote.ZooApi
import repository.ApiRepository
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    private val _zooLibraryData1 = MutableStateFlow<List<AnimalResultItem>>(emptyList())
    val zooLibraryData1: SharedFlow<List<AnimalResultItem>> = _zooLibraryData1.asStateFlow()

    init {
        getData1()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text


    private fun getData1(){
        viewModelScope.launch {
            val ddd = repository.getZooData().result?.results.orEmpty()
            _zooLibraryData1.tryEmit(ddd)
            Log.d("58_789", "getData1: $ddd")
//            repository.networkCall("resourceAquire")
        }
    }

}