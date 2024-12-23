package viewModel

import Data.AnimalResultItem
import android.util.Log
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
import remote.ZooApi
import repository.ApiRepository
import javax.inject.Inject


@HiltViewModel
class SlideshowViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ApiRepository,
): ViewModel() {

    private val _zooLibraryData1 = MutableStateFlow<List<AnimalResultItem>>(emptyList())
    val zooLibraryData1: SharedFlow<List<AnimalResultItem>> = _zooLibraryData1.asStateFlow()

    private val _animalTitle = MutableStateFlow<String>("")
    val animalTitle: SharedFlow<String> = _animalTitle.asStateFlow()

    init {
        getData1()
//        getPassData()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text

    private fun getData1(){
        viewModelScope.launch {
            val ddd = repository.getZooData().result?.results.orEmpty()
            _zooLibraryData1.tryEmit(ddd)
            Log.d("68_789", "getData2: $ddd")
        }
    }

    fun getPassData(navController: NavController){
        _animalTitle.tryEmit(navController.previousBackStackEntry?.savedStateHandle?.get<String>("Title").orEmpty())
        Log.d("52_789", "getPassData: ${_animalTitle.value}")
    }
}