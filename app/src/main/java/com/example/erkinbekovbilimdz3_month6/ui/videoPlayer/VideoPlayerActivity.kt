package com.example.erkinbekovbilimdz3_month6.ui.videoPlayer

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.erkinbekovbilimdz3_month6.core.base.BaseActivity
import com.example.erkinbekovbilimdz3_month6.core.network.InternetConnection
import com.example.erkinbekovbilimdz3_month6.core.network.Resource
import com.example.erkinbekovbilimdz3_month6.databinding.ActivityVideoPlayerBinding
import com.example.erkinbekovbilimdz3_month6.ui.detailPlaylist.DetailPlaylistActivity
import com.example.erkinbekovbilimdz3_month6.ui.videoPlayer.videoPlayerViewModel.VideoPlayerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoPlayerActivity : BaseActivity<ActivityVideoPlayerBinding, VideoPlayerViewModel>() {


    override val viewModel: VideoPlayerViewModel by viewModel()
    private lateinit var internetConnection: InternetConnection
    override fun inflateViewBinding() = ActivityVideoPlayerBinding.inflate(layoutInflater)


    @SuppressLint("SetJavaScriptEnabled")
    override fun setupLiveData() {
        super.setupLiveData()
        binding.webViewPreview.settings.javaScriptEnabled = true
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }
        intent.getStringExtra(DetailPlaylistActivity.KEY_FOR_VIDEO).let { videoId ->
            videoId?.let {
                viewModel.getVideo(it).observe(this) {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            binding.apply {
                                it.data?.items!![0].snippet.title.also { tvVideoName.text = it }
                                it.data.items[0].snippet.description.also {
                                    tvDescription.text = it
                                }
                                binding.webViewPreview.loadUrl("https://www.youtube.com/embed/${it.data.items[0].id}")
                            }
                            viewModel.loading.postValue(false)
                        }
                        Resource.Status.ERROR -> {
                            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                            viewModel.loading.postValue(false)
                        }
                        Resource.Status.LOADING -> {
                            viewModel.loading.postValue(true)
                        }
                    }
                }
            }
        }
    }

    override fun initClickListener() {
        super.initClickListener()
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun checkInternet() {
        super.checkInternet()
        internetConnection = InternetConnection(this)
        internetConnection.observe(this) {
            if (it) {
                binding.clNoInternet.isVisible = false
                binding.llMainActivity.isVisible = true
            } else {
                binding.clNoInternet.isVisible = true
                binding.llMainActivity.isVisible = false
            }
        }
    }
}