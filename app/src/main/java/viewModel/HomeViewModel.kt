package viewModel

import Data.ZooResultItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
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

    private val _zooLibraryData = MutableStateFlow<List<ZooResultItem>>(emptyList())
    val zooLibraryData: SharedFlow<List<ZooResultItem>> = _zooLibraryData.asStateFlow()

    private fun getData(){
        viewModelScope.launch {
            val libraryData = repository.networkCall().result?.results.orEmpty()
            _zooLibraryData.tryEmit(libraryData)
        }
    }

    fun saveArgs(
        navController: NavController,
        title: String = "",
        imgUrl: String = "",
        libraryContent: String = "",
        memo: String = "",
        category: String = "",
        eUrl: String = "",
    ) {
        navController.currentBackStackEntry?.savedStateHandle?.set("title", title)
        navController.currentBackStackEntry?.savedStateHandle?.set("imgUrl", imgUrl)
        navController.currentBackStackEntry?.savedStateHandle?.set("libraryContent", libraryContent)
        navController.currentBackStackEntry?.savedStateHandle?.set("memo", memo)
        navController.currentBackStackEntry?.savedStateHandle?.set("category", category)
        navController.currentBackStackEntry?.savedStateHandle?.set("eUrl", eUrl)
    }
}