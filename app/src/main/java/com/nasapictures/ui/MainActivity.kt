package com.nasapictures.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nasapictures.R
import com.nasapictures.databinding.ActivityMainBinding
import com.nasapictures.utils.ItemOffsetDecoration
import com.nasapictures.viewmodel.MediaViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: MediaAdapter = MediaAdapter(arrayListOf())
    private lateinit var viewModel: MediaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(MediaViewModel::class.java)

        initViews()
        observerLiveData()
        loadData()
    }

    private fun loadData() {
        viewModel.getMediaItems()
    }

    private fun observerLiveData() {
        viewModel.mediaItems.observe(this) {
            Log.d("MainActivity", "size ${it?.size}")
            adapter.addMoreItems(it.orEmpty())
            showProgressBar(false)
        }
    }

    private fun initViews() {
        showProgressBar(true)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2)
        binding.mediaList.layoutManager = layoutManager
        binding.mediaList.addItemDecoration(ItemOffsetDecoration(this,   R.dimen.item_offset))
        binding.mediaList.adapter = adapter
    }

    private fun showProgressBar(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }
}