package viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import remote.ZooApi
import repository.ApiRepository
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
//    private val repository: ApiRepository
): ViewModel() {

    init {
        getData()
//        repository.networkCall("resourceAquire")
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getData(){
        viewModelScope.launch {
//            repository.networkCall("resourceAquire")
        }
    }
}