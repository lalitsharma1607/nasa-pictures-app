package com.nasapictures.repository.model

import java.text.SimpleDateFormat
import java.util.*

data class MediaItem(
    val copyright: String?,
    val date: String?,
    val explanation: String?,
    val hdurl: String?,
    val mediaType: String,
    val serviceVersion: String?,
    val title: String?,
    val url: String?
) {
    fun getDate(): Date? {
        val sdf = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
        return date?.let { sdf.parse(it) }
    }
}
