package com.example.newsapp.ui.fragments.articlefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsapp.databinding.FragmentArticleBinding

class ArticleFragment: Fragment() {

    private lateinit var binding:FragmentArticleBinding
    val args:ArticleFragmentArgs by navArgs()

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

        return binding.root
    }
}