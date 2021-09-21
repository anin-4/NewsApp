package com.example.newsapp.ui.fragments.savednewsfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.domain.NewsAppDomain
import com.example.newsapp.ui.fragments.ArticleViewHolder

class SavedNewsRecyclerViewAdapter:RecyclerView.Adapter<SavedNewsRecyclerViewHolder>() {
    var items= listOf<NewsAppDomain>()
        set(value){
            field=value
            notifyDataSetChanged()
        }

    var itemClickListener:((view: View, item:NewsAppDomain, position:Int)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsRecyclerViewHolder {
        val binding=
            ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SavedNewsRecyclerViewHolder.ArticleItem(binding)
    }

    override fun onBindViewHolder(holder: SavedNewsRecyclerViewHolder, position: Int) {
        when(holder){
            is SavedNewsRecyclerViewHolder.ArticleItem -> holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    }
