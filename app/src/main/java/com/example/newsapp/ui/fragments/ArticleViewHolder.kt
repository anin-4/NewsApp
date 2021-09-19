package com.example.newsapp.ui.fragments

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.domain.NewsAppDomain
import com.example.newsapp.loadImage

sealed class ArticleViewHolder(binding:ItemArticlePreviewBinding): RecyclerView.ViewHolder(binding.root){

        class ArticleItem( private val binding: ItemArticlePreviewBinding):ArticleViewHolder(binding){
            fun bind(item:NewsAppDomain){
                binding.tvTitle.text=item.title.toString()
                binding.tvDescription.text=item.description.toString()
                binding.tvSource.text=item.author.toString()
                binding.tvPublishedAt.text=item.publishedAt.toString()
                item.urlToImage?.let { binding.ivArticleImage.loadImage(it) }
            }
        }

}
