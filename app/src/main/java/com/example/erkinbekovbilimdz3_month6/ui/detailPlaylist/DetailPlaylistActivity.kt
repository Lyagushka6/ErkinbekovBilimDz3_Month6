package com.example.erkinbekovbilimdz3_month6.ui.detailPlaylist

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.erkinbekovbilimdz3_month6.core.base.BaseActivity
import com.example.erkinbekovbilimdz3_month6.core.network.InternetConnection
import com.example.erkinbekovbilimdz3_month6.core.network.Resource
import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel
import com.example.erkinbekovbilimdz3_month6.databinding.ActivityDetailPlaylistBinding
import com.example.erkinbekovbilimdz3_month6.ui.detailPlaylist.adapter.DetailPlaylistAdapter
import com.example.erkinbekovbilimdz3_month6.ui.detailPlaylist.detailPlatlistViewModel.DetailPlaylistViewModel
import com.example.erkinbekovbilimdz3_month6.ui.playList.PlaylistActivity.Companion.KEY_FOR_COUNT
import com.example.erkinbekovbilimdz3_month6.ui.playList.PlaylistActivity.Companion.KEY_FOR_DESC
import com.example.erkinbekovbilimdz3_month6.ui.playList.PlaylistActivity.Companion.KEY_FOR_ID
import com.example.erkinbekovbilimdz3_month6.ui.playList.PlaylistActivity.Companion.KEY_FOR_TITLE
import com.example.erkinbekovbilimdz3_month6.ui.videoPlayer.VideoPlayerActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPlaylistActivity :
    BaseActivity<ActivityDetailPlaylistBinding, DetailPlaylistViewModel>() {


    override val viewModel: DetailPlaylistViewModel by viewModel()
    private lateinit var internetConnection: InternetConnection
    private lateinit var adapter: DetailPlaylistAdapter


    override fun inflateViewBinding() = ActivityDetailPlaylistBinding.inflate(layoutInflater)


    override fun setupLiveData() {
        super.setupLiveData()
        val playlistId = intent.getStringExtra(KEY_FOR_ID)
        viewModel.loading.observe(this) {
            binding.apply {
                progressBar.isVisible = it
            }
        }
        viewModel.getPlaylistItems(playlistId.toString()).observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    adapter = DetailPlaylistAdapter(this::onClick)
                    binding.rvPlaylistDetail.adapter = adapter
                    it.data?.let { it1 -> adapter.setList(it1.items) }
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

    override fun checkInternet() {
        super.checkInternet()
        internetConnection = InternetConnection(this)
        internetConnection.observe(this) {
            if (it) {
                binding.apply {
                    clNoInternet.isVisible = false
                    clMainActivity.isVisible = true
                }

            } else {
                binding.apply {
                    clNoInternet.isVisible = true
                    clMainActivity.isVisible = false
                }
            }
        }
    }

    override fun initClickListener() {
        super.initClickListener()
        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setData() {
        super.setData()
        binding.apply {
            tvCountVideoDetails.text = intent.getStringExtra(KEY_FOR_COUNT) + " video series"
            tvVideoName.text = intent.getStringExtra(KEY_FOR_TITLE)
            tvDescription.text = intent.getStringExtra(KEY_FOR_DESC)
        }
    }

    private fun onClick(item: PlaylistModel.Item) {
        val intent = Intent(this, VideoPlayerActivity::class.java)
        intent.putExtra(KEY_FOR_VIDEO, item.contentDetails.videoId)
        startActivity(intent)
    }

    companion object {
        const val KEY_FOR_VIDEO = "key for videva"
    }
}