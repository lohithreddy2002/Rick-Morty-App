package com.example.rickmorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.example.rickmorty.databinding.FragmentHomeBinding
import com.example.rickmorty.models.Character
import com.example.rickmorty.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var homeBinding: FragmentHomeBinding
    private val viewModel:HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getCharacters()
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onStart() {
        super.onStart()
        homeBinding = FragmentHomeBinding.bind(requireView())
        lifecycleScope.launch {
            viewModel._response.collect {
                when(it){
                    is UiState.Success<*> ->{
                        it.data as Character?
                        homeBinding.image.load(it.data?.image)
                    }
                }
            }

        }

    }
}
