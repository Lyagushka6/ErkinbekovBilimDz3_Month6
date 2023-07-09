package com.example.erkinbekovbilimdz3_month6.data.remote

import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlayLists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int = 50,
    ): Response<PlaylistModel>

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int = 60,
    ): Response<PlaylistModel>
}