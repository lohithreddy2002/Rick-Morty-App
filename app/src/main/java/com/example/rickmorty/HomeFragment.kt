package com.example.rickmorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickmorty.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
        homeBinding = FragmentHomeBinding.bind(requireView())
        viewModel.homeCharacterPaging()
        val recycleAdapter = CharacterPagingAdapter()
        homeBinding.recycle.apply {
            adapter = recycleAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.pageResult.observe(
            this@HomeFragment,
            {
                recycleAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        )
        viewLifecycleOwner.lifecycleScope.launch {
            recycleAdapter.loadStateFlow.collectLatest {
                if (it.source.append is LoadState.Loading) {
                    homeBinding.pb.visibility = View.VISIBLE
                } else {
                    homeBinding.pb.visibility = View.GONE
                }
            }
        }
    }
}
