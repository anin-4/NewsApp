package com.example.newsapp.ui.fragments

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.domain.NewsAppDomain

sealed class ArticleViewHolder(binding:ItemArticlePreviewBinding): RecyclerView.ViewHolder(binding.root){

    var itemClickListener:((view: View, item:NewsAppDomain, position:Int)->Unit)?=null

        class ArticleItem( private val binding: ItemArticlePreviewBinding):ArticleViewHolder(binding){
            fun bind(item:NewsAppDomain){
                binding.tvTitle.text=item.title.toString()
                binding.tvDescription.text=item.description.toString()
                binding.tvSource.text=item.author.toString()
                binding.tvPublishedAt.text=item.publishedAt.toString()
                binding.root.setOnClickListener {
                    itemClickListener?.invoke(it,item,adapterPosition)
                }
            }
        }

}
