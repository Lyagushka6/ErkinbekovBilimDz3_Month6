package com.example.erkinbekovbilimdz3_month6.ui.detailPlaylist

import androidx.lifecycle.LiveData
import com.example.erkinbekovbilimdz3_month6.core.base.BaseViewModel
import com.example.erkinbekovbilimdz3_month6.core.network.Resource
import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel
import com.example.erkinbekovbilimdz3_month6.repository.Repository

class DetailPlaylistViewModel(private val repository: Repository) : BaseViewModel() {
    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistModel>> {
        return repository.getPlaylistItems(playlistId)
    }
}