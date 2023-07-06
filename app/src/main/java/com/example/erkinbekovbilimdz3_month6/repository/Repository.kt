package com.example.erkinbekovbilimdz3_month6.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.erkinbekovbilimdz3_month6.core.network.Resource
import com.example.erkinbekovbilimdz3_month6.core.network.RetrofitClient
import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel
import com.example.erkinbekovbilimdz3_month6.data.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.erkinbekovbilimdz3_month6.utils.Constants.PART
import com.example.erkinbekovbilimdz3_month6.utils.Constants.CHANNEL_ID
import com.example.erkinbekovbilimdz3_month6.BuildConfig.API_KEY

class Repository {

    private val apiService: ApiService = RetrofitClient.create()

    fun getPlaylists(): LiveData<Resource<PlaylistModel>> {
        val data = MutableLiveData<Resource<PlaylistModel>>()

        data.value = Resource.loading()

        apiService.getPlayLists(
            part = PART,
            channelId = CHANNEL_ID,
            apiKey = API_KEY,
            maxResults = 25,
        ).enqueue(object : Callback<PlaylistModel> {
            override fun onResponse(
                call: Call<PlaylistModel>,
                response: Response<PlaylistModel>,
            ) {
                if (response.isSuccessful) {
                    data.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<PlaylistModel>, t: Throwable) {
                data.value = Resource.error(t.message.toString(), null)

            }
        })
        return data
    }

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistModel>> {
        val data = MutableLiveData<Resource<PlaylistModel>>()

        data.value = Resource.loading()

        apiService.getPlaylistItems(
            part = PART,
            playlistId = playlistId,
            apiKey = API_KEY,
            maxResults = 40,
        ).enqueue(object : Callback<PlaylistModel> {
            override fun onResponse(
                call: Call<PlaylistModel>,
                response: Response<PlaylistModel>,
            ) {
                if (response.isSuccessful) {
                    data.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<PlaylistModel>, t: Throwable) {
                data.value = Resource.error(t.message.toString(), null)
            }

        })
        return data
    }

}