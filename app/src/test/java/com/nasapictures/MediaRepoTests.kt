package com.nasapictures

import com.nasapictures.repository.MediaRepo
import com.nasapictures.repository.model.MediaItem
import com.nasapictures.viewmodel.MediaViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MediaRepoTests {
    private lateinit var repo: MediaRepo

    @Before
    fun setUp() {
        repo = mockk()
    }

    @Test
    fun testGetMedia() {
        every { repo.getMedia() } returns arrayListOf<MediaItem>()
        val items = repo.getMedia()
        assertTrue(items?.isEmpty() == true)
    }

    @Test
    fun getMediaMockingBehaviour() {
        every { repo.getMedia() } returns arrayListOf<MediaItem>() andThen arrayListOf<MediaItem>(
            mockk(), mockk()
        ) andThen null
        var items = repo.getMedia()
        assertTrue(items.isNullOrEmpty())

        items = repo.getMedia()
        assertFalse(items.isNullOrEmpty())

        items = repo.getMedia()
        assertEquals(items, null)
    }
}