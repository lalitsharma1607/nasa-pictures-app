package com.nasapictures.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nasapictures.R
import com.nasapictures.databinding.GalleryItemBinding
import com.nasapictures.repository.model.MediaItem

class MediaAdapter(val mediaItems: ArrayList<MediaItem>): RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: GalleryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaAdapter.ViewHolder {
        val binding = GalleryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MediaAdapter.ViewHolder, position: Int) {
        holder.binding.title.text = mediaItems[position].title
        Glide
            .with(holder.binding.root.context)
            .load(mediaItems[position].url)
            .centerCrop()
            .into(holder.binding.img)

    }

    override fun getItemCount(): Int {
        return mediaItems.size
    }

    fun addMoreItems(items: List<MediaItem>) {
        mediaItems.addAll(items)
    }
}