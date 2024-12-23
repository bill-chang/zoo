package viewModel

import Data.AnimalResultItem
import Data.Posts
import Data.ZooResultData
import Data.ZooResultItem
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import remote.ZooApi
import repository.ApiRepository
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ApiRepository
): ViewModel() {

    init {
        getData()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _zooLibraryData = MutableStateFlow<List<ZooResultItem>>(emptyList())
    val zooLibraryData: SharedFlow<List<ZooResultItem>> = _zooLibraryData.asStateFlow()




//    private val _zooLibraryData = MutableStateFlow<List<Posts>>(emptyList())
//    val zooLibraryData: SharedFlow<List<Posts>> = _zooLibraryData.asStateFlow()

    private fun getData(){
        viewModelScope.launch {
            val ddd = repository.networkCall().result?.results.orEmpty()
            _zooLibraryData.tryEmit(ddd)
            Log.d("30_789", "getData: $ddd")
//            repository.networkCall("resourceAquire")
        }
    }
}