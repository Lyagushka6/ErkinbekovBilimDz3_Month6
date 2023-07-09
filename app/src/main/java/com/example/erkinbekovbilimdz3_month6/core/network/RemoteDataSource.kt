package com.example.erkinbekovbilimdz3_month6.core.network

import com.example.erkinbekovbilimdz3_month6.BuildConfig
import com.example.erkinbekovbilimdz3_month6.core.base.BaseDataSource
import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel
import com.example.erkinbekovbilimdz3_month6.data.remote.ApiService
import com.example.erkinbekovbilimdz3_month6.utils.Constants
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getPlayList() = getResult {
        apiService.getPlayLists(
            part = Constants.PART,
            channelId = Constants.CHANNEL_ID,
            apiKey = BuildConfig.API_KEY,
            maxResults = 50,
        )
    }

    suspend fun getPlaylistItems(playlistId: String) = getResult {
        apiService.getPlaylistItems(
            part = Constants.PART,
            playlistId = playlistId,
            apiKey = BuildConfig.API_KEY,
            maxResults = 100,
        )
    }
}