package com.example.newsapp.ui.fragments.searchnewsfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Resource
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.ui.fragments.NewsViewModel
import com.example.newsapp.utilspackage.Constants
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment: Fragment() {

    private lateinit var binding:FragmentSearchNewsBinding
    private val viewModel: NewsViewModel by activityViewModels()
    private val searchNewsRecyclerViewAdapter= SearchNewsRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding= FragmentSearchNewsBinding.inflate(inflater,container,false)

        binding.rvSearchNews.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=searchNewsRecyclerViewAdapter
        }

        var job: Job?=null

        binding.etSearch.addTextChangedListener { editable->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                if(editable.toString().isNotEmpty()){
                    viewModel.getSearchNewsArticles(editable.toString())
                }
            }

        }

        viewModel.searchNewsItems.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    binding.paginationProgressBar.visibility=View.VISIBLE
                }
                is Resource.Success -> {
                    binding.paginationProgressBar.visibility=View.INVISIBLE
                    it.data?.let{ response ->
                        searchNewsRecyclerViewAdapter.items=response.articles
                    }
                }
                is Resource.Error -> {
                    binding.paginationProgressBar.visibility=View.INVISIBLE
                    Log.e(Constants.TAG, "onCreateView: no data in the api", )
                }
                else -> {
                    Log.e(Constants.TAG, "onCreateView: the code should not reach here", )
                }
            }
        }

        return binding.root
    }


}