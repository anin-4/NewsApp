package com.example.newsapp.ui.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.utilspackage.Constants.TAG
import com.example.newsapp.domain.NewsAppDomain
import com.example.newsapp.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private var repository:RepositoryInterface
): ViewModel() {

    private var _newsItems= MutableLiveData<List<NewsAppDomain>>()

    val newsItems:LiveData<List<NewsAppDomain>>
    get() = _newsItems

    private var _isLoading= MutableLiveData<Boolean>()

    val isLoading:LiveData<Boolean>
    get()= _isLoading


    init {
        getBreakingNewsArticles()
    }

    private fun getBreakingNewsArticles(){
        viewModelScope.launch {
            _isLoading.value=true
            val response=repository.getListOfNetworkEntityBreaking(1)
            Log.d(TAG, "getBreakingNewsArticles: $response")
            _newsItems.value=response
            _isLoading.value=false
        }
    }



}