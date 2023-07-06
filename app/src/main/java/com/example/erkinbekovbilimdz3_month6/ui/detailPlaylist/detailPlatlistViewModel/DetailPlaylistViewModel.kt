package com.example.erkinbekovbilimdz3_month6.ui.detailPlaylist.detailPlatlistViewModel

import androidx.lifecycle.LiveData
import com.example.erkinbekovbilimdz3_month6.App.Companion.repository
import com.example.erkinbekovbilimdz3_month6.core.base.BaseViewModel
import com.example.erkinbekovbilimdz3_month6.core.network.Resource
import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel

class DetailPlaylistViewModel : BaseViewModel() {
    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistModel>> {
        return repository.getPlaylistItems(playlistId)
    }
}