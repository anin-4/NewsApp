package com.example.newsapp.ui.fragments.breakingnewsfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Resource
import com.example.newsapp.databinding.FragmentBreakingNewsBinding
import com.example.newsapp.ui.fragments.NewsViewModel
import com.example.newsapp.utilspackage.Constants.TAG

class BreakingNewsFragment:Fragment() {
        private lateinit var binding:FragmentBreakingNewsBinding
        private val viewModel: NewsViewModel by activityViewModels<NewsViewModel>()
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

        viewModel.newsItems.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    binding.paginationProgressBar.visibility=View.VISIBLE
                }
                is Resource.Success -> {
                    binding.paginationProgressBar.visibility=View.INVISIBLE
                    it.data?.let{ response ->
                        breakingNewsRecyclerViewAdapter.items=response.articles

                    }
                }

                is Resource.Error -> {
                        binding.paginationProgressBar.visibility=View.INVISIBLE
                    Log.e(TAG, "onCreateView: no data in the api", )
                }
                else -> {
                    Log.e(TAG, "onCreateView: the code should not reach here", )
                }
            }
        }

        return binding.root
    }



}