package com.example.pexelsapp.ui.photos

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pexelsapp.data.model.Photo
import com.example.pexelsapp.databinding.PhotoItemBinding
import com.example.pexels.utils.extensions.loadUrl

class PhotoAdapter(private val listener: PhotoItemListener) :
    RecyclerView.Adapter<PhotoViewHolder>() {

    interface PhotoItemListener {
        fun onClickedPhoto(photoId: Int)
    }


    private val items: MutableList<Photo> = mutableListOf()

    fun setPhotos(photos: List<Photo>) {
        this.items.clear()
        this.items.addAll(photos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding: PhotoItemBinding =
            PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) =
        holder.bind(items[position])

}

class PhotoViewHolder(
    private val vBind: PhotoItemBinding,
    private val listener: PhotoAdapter.PhotoItemListener
) : RecyclerView.ViewHolder(vBind.root),
    View.OnClickListener {

    private lateinit var photo: Photo

    init {
        vBind.root.setOnClickListener(this)

    }

    fun bind(item: Photo) {
        this.photo = item
        vBind.TvPhotographer.text = item.photographer
        vBind.IvPhoto.loadUrl(item.src?.small, 50, 50)
    }

    override fun onClick(v: View?) {
        photo.id?.let { listener.onClickedPhoto(it) }
    }
}

