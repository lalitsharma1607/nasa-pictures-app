package com.nasapictures.repository

import android.app.Application
import com.google.gson.reflect.TypeToken
import com.nasapictures.repository.model.MediaItem
import com.nasapictures.utils.IOUtils
import com.nasapictures.utils.JsonParser
import java.lang.reflect.Type


object MediaRepo {
    private var mediaItems: List<MediaItem>? = null

    fun loadMedia(application: Application) {
        val jsonString = IOUtils.getJsonFromAssets(application)
        val listMediaType: Type = object :
            TypeToken<List<MediaItem>?>() {}.type
        mediaItems = JsonParser.parseJson(jsonString, listMediaType)
    }

    fun getMedia(): List<MediaItem>? {
        return mediaItems
    }
}