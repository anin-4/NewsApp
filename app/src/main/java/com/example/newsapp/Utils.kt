package com.example.newsapp

import android.net.Uri
import com.facebook.drawee.view.SimpleDraweeView


fun SimpleDraweeView.loadImage(url: String) {
    this.setImageURI(Uri.parse(url))
}