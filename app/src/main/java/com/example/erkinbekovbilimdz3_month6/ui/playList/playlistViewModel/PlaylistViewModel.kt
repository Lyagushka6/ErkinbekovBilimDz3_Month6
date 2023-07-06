package com.example.erkinbekovbilimdz3_month6.ui.playList.playlistViewModel

import androidx.lifecycle.LiveData
import com.example.erkinbekovbilimdz3_month6.App.Companion.repository
import com.example.erkinbekovbilimdz3_month6.core.base.BaseViewModel
import com.example.erkinbekovbilimdz3_month6.core.network.Resource
import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel


class PlaylistViewModel() : BaseViewModel() {
    fun getPlaylist(): LiveData<Resource<PlaylistModel>> {
        return repository.getPlaylists()
    }
}