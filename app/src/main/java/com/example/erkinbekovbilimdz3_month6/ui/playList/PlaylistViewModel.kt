package com.example.erkinbekovbilimdz3_month6.ui.playList

import androidx.lifecycle.LiveData
import com.example.erkinbekovbilimdz3_month6.core.base.BaseViewModel
import com.example.erkinbekovbilimdz3_month6.core.network.Resource
import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel
import com.example.erkinbekovbilimdz3_month6.repository.Repository


class PlaylistViewModel(private val repository: Repository) : BaseViewModel() {
    fun getPlaylist(): LiveData<Resource<PlaylistModel>> {
        return repository.getPlayLists()
    }
}