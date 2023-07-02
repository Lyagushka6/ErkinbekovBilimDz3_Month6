package com.example.erkinbekovbilimdz3_month6.data.remote

import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlayLists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int = 25,
    ): Call<PlaylistModel>
}