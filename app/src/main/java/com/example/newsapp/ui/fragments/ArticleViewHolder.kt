package com.example.newsapp.ui.fragments


import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.loadImage
import com.example.newsapp.network.response.Article

sealed class ArticleViewHolder(binding:ItemArticlePreviewBinding): RecyclerView.ViewHolder(binding.root){

        class ArticleItem( private val binding: ItemArticlePreviewBinding):ArticleViewHolder(binding){
            fun bind(item:Article){
                binding.tvTitle.text=item.title
                binding.tvDescription.text=item.description
                binding.tvSource.text=item.author
                binding.tvPublishedAt.text=item.publishedAt
                item.urlToImage?.let { binding.ivArticleImage.loadImage(it) }
            }
        }

}
