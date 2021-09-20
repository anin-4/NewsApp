package com.example.newsapp.ui.fragments


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.loadImage
import com.example.newsapp.network.response.Article

sealed class ArticleViewHolder(binding: ItemArticlePreviewBinding): RecyclerView.ViewHolder(binding.root){

    var itemClickListener:((view: View, item:Article, position:Int)->Unit)?=null

        class ArticleItem( private val binding: ItemArticlePreviewBinding):ArticleViewHolder(binding){
            fun bind(item:Article){
                binding.tvTitle.text=item.title
                binding.tvDescription.text=item.description
                if(item.author!=null && item.author.length>22){
                    val newItemAuthor=item.author+"..."
                    binding.tvSource.text=newItemAuthor
                }
                else{
                    binding.tvSource.text=item.author
                }
                binding.tvPublishedAt.text=item.publishedAt
                item.urlToImage?.let { binding.ivArticleImage.loadImage(it) }

                binding.root.setOnClickListener {
                    itemClickListener?.let { it1 -> it1(it,item,adapterPosition) }
                }
            }

        }

}
