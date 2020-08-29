package com.example.pexelsapp.utils.extensions


import android.view.View
import android.widget.ImageView
import com.example.pexelsapp.R
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String?, width: Int, height: Int) {
    Picasso.with(context)
        .load(url)
        .resize(width, height)
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_foreground)
        .into(this)
}


fun View.showView(show: Boolean? = true) {
    visibility = when (show) {
        true -> View.VISIBLE
        false -> View.GONE
        else -> View.INVISIBLE
    }
}