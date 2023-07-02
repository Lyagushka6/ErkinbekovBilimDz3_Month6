package com.example.erkinbekovbilimdz3_month6.ui


import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.erkinbekovbilimdz3_month6.core.network.InternetConnection
import com.example.erkinbekovbilimdz3_month6.core.base.BaseActivity
import com.example.erkinbekovbilimdz3_month6.core.network.Resource
import com.example.erkinbekovbilimdz3_month6.databinding.ActivityPlaylistBinding
import com.example.erkinbekovbilimdz3_month6.ui.adapter.PlaylistAdapter
import com.example.erkinbekovbilimdz3_month6.viewModel.PlaylistViewModel


class PlaylistActivity : BaseActivity<ActivityPlaylistBinding, PlaylistViewModel>() {

    override val viewModel: PlaylistViewModel by viewModels()
    private lateinit var internetConnection: InternetConnection
    private lateinit var adapter: PlaylistAdapter

    override fun inflateViewBinding() = ActivityPlaylistBinding.inflate(layoutInflater)

    override fun setupLiveData() {
        super.setupLiveData()
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }
        viewModel.getPlaylist().observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    adapter = PlaylistAdapter()
                    binding.rvPlaylist.adapter = adapter
                    it.data?.let { it1 -> adapter.addList(it1.items) }
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
                    binding.clNoInternet.isVisible = false
                    binding.llMainActivity.isVisible = true
            } else {
                binding.clNoInternet.isVisible = true
                binding.llMainActivity.isVisible = false
            }
        }
    }
}