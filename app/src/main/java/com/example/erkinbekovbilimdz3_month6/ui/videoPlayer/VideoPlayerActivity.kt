package com.example.erkinbekovbilimdz3_month6.ui.videoPlayer

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.erkinbekovbilimdz3_month6.BuildConfig
import com.example.erkinbekovbilimdz3_month6.core.base.BaseActivity
import com.example.erkinbekovbilimdz3_month6.core.network.InternetConnection
import com.example.erkinbekovbilimdz3_month6.core.network.Resource
import com.example.erkinbekovbilimdz3_month6.databinding.ActivityVideoPlayerBinding
import com.example.erkinbekovbilimdz3_month6.databinding.DownloadAlertDialogBinding
import com.example.erkinbekovbilimdz3_month6.ui.detailPlaylist.DetailPlaylistActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class VideoPlayerActivity : BaseActivity<ActivityVideoPlayerBinding, VideoPlayerViewModel>() {

    private lateinit var internetConnection: InternetConnection
    private lateinit var dialogBinding: DownloadAlertDialogBinding
    override val viewModel: VideoPlayerViewModel by viewModel()

    private fun inflateDialogBinding() {
        dialogBinding = DownloadAlertDialogBinding.inflate(layoutInflater)
    }

    override fun inflateViewBinding(): ActivityVideoPlayerBinding {
        inflateDialogBinding()
        return ActivityVideoPlayerBinding.inflate(layoutInflater)
    }


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
                                it.data?.items?.first()?.snippet?.title.also {
                                    tvVideoName.text = it
                                }
                                it.data?.items?.first()?.snippet?.description.also {
                                    tvDescription.text = it
                                }
                                binding.webViewPreview.loadUrl("${BuildConfig.PLAYER_URL}${it.data?.items?.first()?.id}")
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
        binding.btnDownload.setOnClickListener {
            showDownloadDialog()
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

    private fun showDownloadDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogBinding.root)

        val dialog = dialogBuilder.create()

        val parentView = dialogBinding.root.parent as? ViewGroup
        parentView?.removeView(dialogBinding.root)

        dialogBinding.btnDownload.setOnClickListener {
            val selectedQuality = when (dialogBinding.radioGroup.checkedRadioButtonId) {
                dialogBinding.radioButton480p.id -> "480p"
                dialogBinding.radioButton720.id -> "720p"
                dialogBinding.radioButton1080p.id -> "1080p"
                else -> "null"
            }

            val videoId = intent.getStringExtra(DetailPlaylistActivity.KEY_FOR_VIDEO)
            val videoTitle = binding.tvVideoName.text.toString()

            if (videoId != null) {
                downloadVideo(videoId, selectedQuality, videoTitle)
            }

            dialog.dismiss()
        }

        dialog.show()
    }

    private fun downloadVideo(videoId: String, quality: String, videoTitle: String) {
        val youtubeUrl = "https://www.youtube.com/watch?v=$videoId"
        val fileName = "${UUID.randomUUID()}.mp4"
        val downloadUrl = "$youtubeUrl&quality=$quality"

        val request = DownloadManager.Request(Uri.parse(downloadUrl))
            .setTitle("$videoTitle ($quality)")
            .setDescription("Downloading video")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalFilesDir(this, null, fileName)

        val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)

        Toast.makeText(this, "Скачивается...", Toast.LENGTH_SHORT).show()
    }
}
