package viewModel

import Data.AnimalResultItem
import Data.ZooResultItem
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
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
    private val savedStateHandle: SavedStateHandle,
    private val repository: ApiRepository
) : ViewModel() {

    private val _zooLibraryData1 = MutableStateFlow<List<AnimalResultItem>>(emptyList())
    val zooLibraryData1: SharedFlow<List<AnimalResultItem>> = _zooLibraryData1.asStateFlow()

    private val _zooAnimalsData2 = MutableStateFlow<List<ZooResultItem>>(emptyList())
    val zooAnimalsData1: SharedFlow<List<ZooResultItem>> = _zooAnimalsData2.asStateFlow()

    init {
        getData()
        getData1()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text

    private fun getData(){
        viewModelScope.launch {
            _zooAnimalsData2.tryEmit(repository.networkCall().result?.results.orEmpty())
        }
    }


    private fun getData1(){
        viewModelScope.launch {
            _zooLibraryData1.tryEmit(repository.getZooData().result?.results.orEmpty())
        }
    }

}