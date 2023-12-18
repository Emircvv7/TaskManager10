package com.example.taskmanager.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun Context.showToast(msg: String) {
    Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url:String?) {
    Glide.with(this).load(url).into(this)
}