package com.example.rickmorty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.utils.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class viewmodel @Inject constructor (private val service: Service,private val dispatcher: CoroutineDispatcher) :ViewModel() {
    fun x() {
        Timber.e("test")
        viewModelScope.launch {
            Timber.e("test")
            Timber.e("${service.getAllCharacters()}")
            safeApiCall(dispatcher){
                service.getAllCharacters()
            }
        }
    }
}