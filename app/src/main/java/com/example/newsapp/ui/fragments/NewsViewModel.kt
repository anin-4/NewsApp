package com.example.newsapp.ui.fragments


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Resource
import com.example.newsapp.network.response.NetworkResponse
import com.example.newsapp.repository.RepositoryInterface
import com.example.newsapp.utilspackage.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private var repository:RepositoryInterface
): ViewModel() {

    private var _newsItems= MutableLiveData<Resource<NetworkResponse>>()

    val newsItems:LiveData<Resource<NetworkResponse>>
    get() = _newsItems

    var breakingNewsPageNumber=1

    var searchNewsPageNumber=1

    var breakingNewsResponse:NetworkResponse?=null

    var searchNewsResponse:NetworkResponse?=null


    private var _searchNewsItems= MutableLiveData<Resource<NetworkResponse>>()

    val searchNewsItems:LiveData<Resource<NetworkResponse>>
    get()= _searchNewsItems

    init {
        getBreakingNewsArticles()
    }

     fun getBreakingNewsArticles(){
        viewModelScope.launch {
            Log.d(TAG, "getBreakingNewsArticles: hit")
            _newsItems.postValue(Resource.Loading())
            val response=repository.getBreakingNews(breakingNewsPageNumber,"us")
            _newsItems.postValue(handleBreakingNewsResponse(response))
        }
    }

    fun getSearchNewsArticles(query:String=""){
        viewModelScope.launch {
            _searchNewsItems.postValue(Resource.Loading())
            val response= repository.getSearchNews(query,searchNewsPageNumber)
            _searchNewsItems.postValue(handleSearchNewsResponse(response))
        }
    }

    private fun handleBreakingNewsResponse(item: Response<NetworkResponse>):Resource<NetworkResponse>{
        if(item.isSuccessful){
            item.body()?.let{
                breakingNewsPageNumber+=1
                if(breakingNewsResponse==null){
                    breakingNewsResponse=it
                }
                else{
                    val oldResponse=breakingNewsResponse?.articles
                    val newResponse=it.articles
                    oldResponse?.addAll(newResponse)
                }
                Log.d(TAG, "handleBreakingNewsResponse: ${breakingNewsResponse?.articles?.size}")
                return Resource.Success(breakingNewsResponse?:it)
            }
        }
        return Resource.Error(msg="Not connected to internet")
    }

    private fun handleSearchNewsResponse(item: Response<NetworkResponse>):Resource<NetworkResponse>{
        if(item.isSuccessful){
            item.body()?.let{
                searchNewsPageNumber++
                if(searchNewsResponse==null){
                    searchNewsResponse=it
                }
                else{
                    val oldResponse=searchNewsResponse?.articles
                    val newResponse=it.articles
                    oldResponse?.addAll(newResponse)
                }
                return Resource.Success(searchNewsResponse?:it)
            }
        }
        return Resource.Error(msg="Not connected to internet")
    }



}