package com.example.erkinbekovbilimdz3_month6.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.erkinbekovbilimdz3_month6.core.network.Resource
import com.example.erkinbekovbilimdz3_month6.core.network.RetrofitClient
import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel
import com.example.erkinbekovbilimdz3_month6.data.remote.ApiService
import com.example.erkinbekovbilimdz3_month6.core.network.RemoteDataSource
import kotlinx.coroutines.Dispatchers

class Repository {

    private val apiService: ApiService = RetrofitClient.create()
    private val remoteDataSource: RemoteDataSource = RemoteDataSource(apiService)

    fun getPlayLists(): LiveData<Resource<PlaylistModel>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val data = remoteDataSource.getPlayList()
            emit(data)
        }
    }

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistModel>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val data = remoteDataSource.getPlaylistItems(playlistId)
            emit(data)
        }
    }
}