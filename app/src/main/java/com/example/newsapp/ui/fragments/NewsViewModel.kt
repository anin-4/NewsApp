package com.example.newsapp.ui.fragments


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Resource
import com.example.newsapp.network.response.NetworkResponse
import com.example.newsapp.repository.RepositoryInterface
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

    init {
        getBreakingNewsArticles()
    }

    private fun getBreakingNewsArticles(){
        viewModelScope.launch {
            _newsItems.postValue(Resource.Loading())
            val response=repository.getBreakingNews(breakingNewsPageNumber,"us")
            _newsItems.postValue(handleBreakingNewsResponse(response))
        }
    }

    private fun handleBreakingNewsResponse(item: Response<NetworkResponse>):Resource<NetworkResponse>{
        if(item.isSuccessful){
            item.body()?.let{
                return Resource.Success(it)
            }
        }
        return Resource.Error(msg="Not connected to internet")
    }



}