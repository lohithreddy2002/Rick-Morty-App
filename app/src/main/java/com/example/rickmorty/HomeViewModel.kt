package com.example.rickmorty

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickmorty.models.Character
import com.example.rickmorty.utils.ResultWrapper
import com.example.rickmorty.utils.UiState
import com.example.rickmorty.utils.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val homeRepository: HomeRepository) : ViewModel() {

    val x = MutableStateFlow<UiState>(UiState.Empty)
    val _response = x.asStateFlow()
    val result = MutableLiveData<Character?>()
    fun getAllCharacters() {
        viewModelScope.launch {

           when(val response = homeRepository.getAllCharacters()){
               is ResultWrapper.Success -> {

               }
               is ResultWrapper.NetworkError -> {

               }
               is ResultWrapper.GenericError -> {

               }


           }

        }
    }
    val pagresult = MutableLiveData<PagingData<Character>>()

    @InternalCoroutinesApi
     fun xi(){
        viewModelScope.launch {
            homeRepository.getCharacters().cachedIn(viewModelScope).flowOn(Dispatchers.IO).collectLatest {
                pagresult.value = it
            }

        }
    }

    fun getCharacters() {
        viewModelScope.launch {
            when(val response = homeRepository.getCharacter()){
                is ResultWrapper.Success -> {
                    x.value = UiState.Success(response.value)
                    result.postValue(response.value)
                }
                is ResultWrapper.NetworkError -> {

                }
                is ResultWrapper.GenericError -> {

                }


            }

        }
    }

}
