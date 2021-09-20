package com.example.newsapp

import android.net.Uri
import com.facebook.drawee.view.SimpleDraweeView


fun SimpleDraweeView.loadImage(url: String) {
    this.setImageURI(Uri.parse(url))
}

sealed class Resource<T>(
    var data:T?=null,
    var msg:String?=null){

    class Success<T>(data:T):Resource<T>(data)

    class Error<T>(data:T?=null, msg:String):Resource<T>(data,msg)

    class Loading<T>():Resource<T>()

}