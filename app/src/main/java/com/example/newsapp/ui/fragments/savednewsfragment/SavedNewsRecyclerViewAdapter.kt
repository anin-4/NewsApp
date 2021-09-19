package com.example.newsapp.ui.fragments.savednewsfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.domain.NewsAppDomain
import com.example.newsapp.ui.fragments.ArticleViewHolder

class SavedNewsRecyclerViewAdapter:RecyclerView.Adapter<ArticleViewHolder>() {
    var items= mutableListOf<NewsAppDomain>()
        set(value){
            field=value
            notifyDataSetChanged()
        }

    var itemClickListener:((view: View, item:NewsAppDomain, position:Int)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding=
            ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder.ArticleItem(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        when(holder){
            is ArticleViewHolder.ArticleItem -> holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}