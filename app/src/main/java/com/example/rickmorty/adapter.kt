package com.example.rickmorty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.databinding.RecycleitemBinding
import com.example.rickmorty.models.Character

class adapter : PagingDataAdapter<Character, adapter.vh>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class vh(private val itemBinding: RecycleitemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(x: Character) {
            itemBinding.name.text = x.name
        }
    }

    override fun onBindViewHolder(holder: vh, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {
        return vh(RecycleitemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }
}