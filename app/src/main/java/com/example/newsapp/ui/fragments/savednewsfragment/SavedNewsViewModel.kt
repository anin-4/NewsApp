package com.example.newsapp.ui.fragments.savednewsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.NewsAppDomain
import com.example.newsapp.network.response.Article
import com.example.newsapp.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private var repository: RepositoryInterface
):ViewModel() {

    private var _savedItems:MutableLiveData<List<NewsAppDomain>> = MutableLiveData()

    val savedItems: LiveData<List<NewsAppDomain>>
    get()=_savedItems

    init{
        getSavedItems()
    }

    private fun getSavedItems(){
        viewModelScope.launch {
            val response= repository.getListFromDatabase()
            _savedItems.value=response
        }
    }


    fun saveIntoDataBase(item:Article){
        viewModelScope.launch {
            repository.pushIntoDatabase(item)
            val response=repository.getListFromDatabase()
            _savedItems.value=response
        }
    }

}