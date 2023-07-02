package com.example.erkinbekovbilimdz3_month6.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.erkinbekovbilimdz3_month6.BuildConfig
import com.example.erkinbekovbilimdz3_month6.core.network.Resource
import com.example.erkinbekovbilimdz3_month6.core.network.RetrofitClient
import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel
import com.example.erkinbekovbilimdz3_month6.data.remote.ApiService
import com.example.erkinbekovbilimdz3_month6.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val apiService: ApiService = RetrofitClient.create()

    fun getPlaylists(): LiveData<Resource<PlaylistModel>> {
        val data = MutableLiveData<Resource<PlaylistModel>>()

        data.value = Resource.loading()

        apiService.getPlayLists(
            part = Constants.PART,
            channelId = Constants.CHANNEL_ID,
            apiKey = BuildConfig.API_KEY,
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
}