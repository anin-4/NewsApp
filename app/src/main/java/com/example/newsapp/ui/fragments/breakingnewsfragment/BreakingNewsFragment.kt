package com.example.newsapp.ui.fragments.breakingnewsfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Constants.TAG
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentBreakingNewsBinding

class BreakingNewsFragment:Fragment() {
        private lateinit var binding:FragmentBreakingNewsBinding
        private val viewModel:BreakingNewsViewModel by activityViewModels<BreakingNewsViewModel>()
        private var breakingNewsRecyclerViewAdapter:BreakingNewsRecyclerViewAdapter=BreakingNewsRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding= FragmentBreakingNewsBinding.inflate(inflater,container,false)

        binding.rvBreakingNews.apply{
            layoutManager=LinearLayoutManager(activity)
            adapter=breakingNewsRecyclerViewAdapter
        }

        viewModel.newsItems.observe(viewLifecycleOwner, {
            breakingNewsRecyclerViewAdapter.items=it
        })

        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it)binding.paginationProgressBar.visibility=View.VISIBLE else binding.paginationProgressBar.visibility=View.INVISIBLE
        }
        return binding.root
    }



}