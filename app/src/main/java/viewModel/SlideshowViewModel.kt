package viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import remote.ZooApi
import repository.ApiRepository
import javax.inject.Inject


@HiltViewModel
class SlideshowViewModel @Inject constructor(
//    private val repository: Lazy<ApiRepository>
): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text
}