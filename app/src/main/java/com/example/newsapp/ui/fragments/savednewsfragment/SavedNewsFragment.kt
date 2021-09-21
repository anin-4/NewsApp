package com.example.newsapp.ui.fragments.savednewsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSavedNewsBinding

class SavedNewsFragment:Fragment() {

    private lateinit var binding:FragmentSavedNewsBinding
    private val viewModel:SavedNewsViewModel by activityViewModels()
    private val savedNewsRecyclerViewAdapter= SavedNewsRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding= FragmentSavedNewsBinding.inflate(inflater,container,false)

        binding.rvSavedNews.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=savedNewsRecyclerViewAdapter
        }

//        savedNewsRecyclerViewAdapter.itemClickListener={view,item,position ->
//
//
//        }

        viewModel.savedItems.observe(viewLifecycleOwner){
            savedNewsRecyclerViewAdapter.items=it
        }



        return binding.root
    }
}