package com.example.rickmorty

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickmorty.models.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val homeRepository: HomeRepository) : ViewModel() {

    val pageResult = MutableLiveData<PagingData<Character>>()

    @InternalCoroutinesApi
    fun homeCharacterPaging() {
        viewModelScope.launch {
            homeRepository.getCharacters().cachedIn(viewModelScope).flowOn(Dispatchers.IO).collectLatest {
                pageResult.value = it
            }
        }
    }
}
