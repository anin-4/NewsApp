package com.example.newsapp.ui.fragments.savednewsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.network.response.Article



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

        savedNewsRecyclerViewAdapter.itemClickListener={_,item,_ ->
            val item1=Article(item.author!!,item.content!!,item.description!!,item.publishedAt!!,item.source!!,item.title!!,item.url!!,item.urlToImage!!)
            val bundle=Bundle().apply {
                putSerializable("article",item1)
            }
            findNavController().navigate(R.id.action_savedNewsFragment_to_articleFragment,bundle)

        }

        viewModel.savedItems.observe(viewLifecycleOwner){
            savedNewsRecyclerViewAdapter.items=it
        }



        return binding.root
    }
}