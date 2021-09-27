package com.example.rickmorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.rickmorty.databinding.FragmentHomeBinding
import com.example.rickmorty.models.Character
import com.example.rickmorty.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var homeBinding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
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
        viewModel.xi()
        val recycleadapter = adapter()
        homeBinding.recycle.apply{
            adapter = recycleadapter
            layoutManager = LinearLayoutManager(requireContext())
        }

            viewModel.pagresult.observe(this@HomeFragment, {
                recycleadapter.submitData(viewLifecycleOwner.lifecycle,it)

            })



    }
}
