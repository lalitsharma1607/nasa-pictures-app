package com.nasapictures.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nasapictures.repository.MediaRepo
import com.nasapictures.repository.model.MediaItem

class MediaViewModel(val app: Application): AndroidViewModel(app) {

    init {
        loadData()
    }
    private var _mediaItems = MutableLiveData<List<MediaItem>?>()
    val mediaItems: LiveData<List<MediaItem>?> = _mediaItems

    fun getMediaItems() {
        _mediaItems.value = MediaRepo.getMedia()
    }

    private fun loadData() = MediaRepo.loadMedia(app)
}