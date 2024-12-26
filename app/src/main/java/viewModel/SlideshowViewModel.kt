package viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import repository.ApiRepository
import javax.inject.Inject


@HiltViewModel
class SlideshowViewModel @Inject constructor(
    private val repository: ApiRepository,
): ViewModel() {

}