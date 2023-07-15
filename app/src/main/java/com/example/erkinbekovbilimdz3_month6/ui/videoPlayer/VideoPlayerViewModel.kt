package com.example.erkinbekovbilimdz3_month6.ui.videoPlayer

import androidx.lifecycle.LiveData
import com.example.erkinbekovbilimdz3_month6.core.base.BaseViewModel
import com.example.erkinbekovbilimdz3_month6.core.network.Resource
import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel
import com.example.erkinbekovbilimdz3_month6.repository.Repository

class VideoPlayerViewModel(private val repository: Repository): BaseViewModel() {
    fun getVideo(videoId: String): LiveData<Resource<PlaylistModel>> {
        return repository.getVideos(videoId)
    }
}