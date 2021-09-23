package com.example.newsapp.ui.fragments.breakingnewsfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.Resource
import com.example.newsapp.databinding.FragmentBreakingNewsBinding
import com.example.newsapp.ui.fragments.NewsRecyclerViewAdapter
import com.example.newsapp.ui.fragments.NewsViewModel
import com.example.newsapp.utilspackage.Constants.TAG

class BreakingNewsFragment:Fragment() {
        private lateinit var binding:FragmentBreakingNewsBinding
        private val viewModel: NewsViewModel by activityViewModels<NewsViewModel>()
        private var breakingNewsRecyclerViewAdapter: NewsRecyclerViewAdapter =
            NewsRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding= FragmentBreakingNewsBinding.inflate(inflater,container,false)


        binding.rvBreakingNews.apply{
            layoutManager=LinearLayoutManager(activity)
            adapter=breakingNewsRecyclerViewAdapter
            addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }


        breakingNewsRecyclerViewAdapter.itemClickListener={_,item,_ ->
                val bundle=Bundle().apply {
                    putSerializable("article",item)
                }
            findNavController().navigate(R.id.action_breakingNewsFragment_to_articleFragment,bundle)
        }

        viewModel.newsItems.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    binding.paginationProgressBar.visibility=View.VISIBLE
                    isLoading=true
                }
                is Resource.Success -> {
                    binding.paginationProgressBar.visibility=View.INVISIBLE
                    isLoading=false
                    it.data?.let{ response ->
                        breakingNewsRecyclerViewAdapter.items=response.articles.toList()
                        val totalPages= response.totalResults/20 + 2
                        isLastPage = totalPages == viewModel.breakingNewsPageNumber

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

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 20
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning
                    isTotalMoreThanVisible && isScrolling
            if(shouldPaginate) {
                viewModel.getBreakingNewsArticles()
                isScrolling = false
            } else {
              binding.rvBreakingNews.setPadding(0, 0, 0, 0)
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }





    }



