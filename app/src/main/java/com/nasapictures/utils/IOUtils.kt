package com.nasapictures.utils

import android.content.Context
import java.io.IOException

object IOUtils {
    private const val assetFileName = "data.json"

    fun getJsonFromAssets(context: Context): String? {
        val jsonString: String = try {
                val inputStream = context.assets.open(assetFileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}