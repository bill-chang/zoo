package viewModel

import Data.AnimalResultItem
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
class GalleryViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    private val _zooAnimalDataList = MutableStateFlow<List<AnimalResultItem>>(emptyList())
    val zooAnimalDataList: SharedFlow<List<AnimalResultItem>> = _zooAnimalDataList.asStateFlow()

    private val _zooAnimalsData3 = MutableStateFlow(ZooResultItem())
    val zooAnimalsData3: SharedFlow<ZooResultItem> = _zooAnimalsData3.asStateFlow()

    init {
        getData1()
    }

    private fun getData1(){
        viewModelScope.launch {
            _zooAnimalDataList.tryEmit(repository.getZooData().result?.results.orEmpty())
        }
    }

    fun saveArgs(
        navController: NavController,
        titleCh: String,
    ) {
        navController.currentBackStackEntry?.savedStateHandle?.set("titleCh", titleCh)
    }

    fun getArgs(navController: NavController){
        val data = ZooResultItem(
            eName = navController.previousBackStackEntry?.savedStateHandle?.get("title"),
            ePicUrl = navController.previousBackStackEntry?.savedStateHandle?.get("imgUrl"),
            eInfo = navController.previousBackStackEntry?.savedStateHandle?.get("libraryContent"),
            eCategory = navController.previousBackStackEntry?.savedStateHandle?.get("category"),
            eMemo = navController.previousBackStackEntry?.savedStateHandle?.get("memo"),
            eUrl = navController.previousBackStackEntry?.savedStateHandle?.get("eUrl"),
        )
        _zooAnimalsData3.tryEmit(data)
    }
}