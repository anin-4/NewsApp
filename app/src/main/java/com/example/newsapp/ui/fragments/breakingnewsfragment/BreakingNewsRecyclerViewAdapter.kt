package com.example.newsapp.ui.fragments.breakingnewsfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.network.response.Article
import com.example.newsapp.ui.fragments.ArticleViewHolder

class BreakingNewsRecyclerViewAdapter:RecyclerView.Adapter<ArticleViewHolder>() {


    //will display data that is received from the api
    var items= listOf<Article>()
        set(value){
        field=value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding=ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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