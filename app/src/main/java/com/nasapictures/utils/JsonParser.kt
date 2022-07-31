package com.nasapictures.utils

import com.google.gson.Gson
import com.nasapictures.repository.model.MediaItem
import java.lang.reflect.Type

object JsonParser {
    fun parseJson(jsonString: String?, listMediaType: Type): List<MediaItem>? {
        val gson = Gson()
        return gson.fromJson(jsonString, listMediaType)
    }
}