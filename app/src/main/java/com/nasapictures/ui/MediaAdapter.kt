package com.nasapictures.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.nasapictures.databinding.GalleryItemBinding
import com.nasapictures.repository.model.MediaItem

class MediaAdapter(private val mediaItems: ArrayList<MediaItem>) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: GalleryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mediaItem: MediaItem) {
            binding.title.text = mediaItem.title
            Glide
                .with(binding.root.context)
                .load(mediaItem.url)
                .centerCrop()
                .into(binding.img)
            binding.root.setOnClickListener {
                binding.root.context.startActivity(
                    MediaDetailActivity.getIntent(
                        binding.root.context,
                        Gson().toJson(mediaItem)
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaAdapter.ViewHolder {
        val binding = GalleryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MediaAdapter.ViewHolder, position: Int) {
        holder.bind(mediaItems[position])
    }

    override fun getItemCount(): Int {
        return mediaItems.size
    }

    fun addMoreItems(items: List<MediaItem>) {
        mediaItems.addAll(items)
    }
}