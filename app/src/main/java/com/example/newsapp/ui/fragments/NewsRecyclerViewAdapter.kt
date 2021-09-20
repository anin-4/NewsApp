package com.example.newsapp.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.network.response.Article
import com.example.newsapp.ui.fragments.ArticleViewHolder

class NewsRecyclerViewAdapter:RecyclerView.Adapter<ArticleViewHolder>() {


    //will display data that is received from the api
    var items= listOf<Article>()
        set(value){
        field=value
        notifyDataSetChanged()
    }

    var itemClickListener:((view: View, item:Article, position:Int)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding=ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder.ArticleItem(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.itemClickListener=itemClickListener
        when(holder){
            is ArticleViewHolder.ArticleItem -> holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}