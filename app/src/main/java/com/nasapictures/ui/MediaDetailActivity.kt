package com.nasapictures.ui

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.gson.Gson
import com.nasapictures.R
import com.nasapictures.databinding.ActivityMediaDetailBinding
import com.nasapictures.repository.model.MediaItem

class MediaDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityMediaDetailBinding
    private var mediaItem: MediaItem? = null

    companion object {
        private const val MEDIA_DETAIL: String = "MEDIA_DETAIL"

        fun getIntent(context: Context, jsonString: String): Intent {
            val intent = Intent(context, MediaDetailActivity::class.java)
            intent.putExtra(MEDIA_DETAIL, jsonString)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonString = intent?.extras?.getString(MEDIA_DETAIL)
        val gson = Gson()
        mediaItem = gson.fromJson(jsonString, MediaItem::class.java)

        setUpView()
    }

    private fun setUpView() {
        title = mediaItem?.title.orEmpty()
        if (!mediaItem?.hdurl.isNullOrEmpty()) {
            Glide
                .with(this)
                .load(mediaItem?.hdurl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        binding.imageLoadingError.visibility = View.VISIBLE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        binding.imageLoadingError.visibility = View.GONE
                        return false
                    }

                })
                .centerCrop()
                .into(binding.fullImg)
        }

        binding.imageDescription.text = mediaItem?.explanation.orEmpty()
        binding.imageCopyright.text = getString(R.string.copyright_prefix, mediaItem?.copyright.orEmpty())
        binding.imageCreatedDate.text = mediaItem?.date.orEmpty()

    }
}