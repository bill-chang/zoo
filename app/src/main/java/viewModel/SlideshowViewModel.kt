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
import repository.ApiRepository
import javax.inject.Inject


@HiltViewModel
class SlideshowViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ApiRepository,
): ViewModel() {

    private val _animalDataList = MutableStateFlow<List<AnimalResultItem>>(emptyList())
    val animalDataList: SharedFlow<List<AnimalResultItem>> = _animalDataList.asStateFlow()

    private val _animalDetailItem = MutableStateFlow<AnimalResultItem>(AnimalResultItem())
    val animalDetailItem: SharedFlow<AnimalResultItem> = _animalDetailItem.asStateFlow()

    init {
        getData1()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text

    private fun getData1(){
        viewModelScope.launch {
            _animalDataList.tryEmit(repository.getZooData().result?.results.orEmpty())
        }
    }

    fun getPassData(navController: NavController){
        _animalDetailItem.tryEmit(
            _animalDataList.value.firstOrNull {
                it.aNameCh == navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                    "titleCh"
                ).orEmpty()
            } ?: AnimalResultItem()
        )
    }
}