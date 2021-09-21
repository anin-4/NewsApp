package com.example.newsapp.ui.fragments.articlefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.ui.fragments.savednewsfragment.SavedNewsViewModel

class ArticleFragment: Fragment() {
    private lateinit var binding:FragmentArticleBinding
    val args:ArticleFragmentArgs by navArgs()
    private val viewModel:SavedNewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding= FragmentArticleBinding.inflate(inflater,container,false)
        val item=args.article
        binding.webView.apply {
            webViewClient= WebViewClient()
            loadUrl(item.url)
        }

        binding.fab.setOnClickListener {
            viewModel.saveIntoDataBase(item)
            Toast.makeText(activity, "Saved To Favourites", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}