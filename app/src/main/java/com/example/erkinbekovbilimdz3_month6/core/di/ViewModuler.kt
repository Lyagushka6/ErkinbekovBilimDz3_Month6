package com.example.erkinbekovbilimdz3_month6.core.di

import com.example.erkinbekovbilimdz3_month6.ui.detailPlaylist.DetailPlaylistViewModel
import com.example.erkinbekovbilimdz3_month6.ui.playList.PlaylistViewModel
import com.example.erkinbekovbilimdz3_month6.ui.videoPlayer.VideoPlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { PlaylistViewModel(get()) }
    viewModel { DetailPlaylistViewModel(get()) }
    viewModel { VideoPlayerViewModel(get()) }
}