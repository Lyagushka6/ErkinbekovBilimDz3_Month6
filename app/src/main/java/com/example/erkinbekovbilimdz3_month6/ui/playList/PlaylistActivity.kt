package com.example.erkinbekovbilimdz3_month6.ui.playList

import android.content.Intent
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.erkinbekovbilimdz3_month6.core.network.InternetConnection
import com.example.erkinbekovbilimdz3_month6.core.base.BaseActivity
import com.example.erkinbekovbilimdz3_month6.core.network.Resource
import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel.Item
import com.example.erkinbekovbilimdz3_month6.databinding.ActivityPlaylistBinding
import com.example.erkinbekovbilimdz3_month6.ui.detailPlaylist.DetailPlaylistActivity
import com.example.erkinbekovbilimdz3_month6.ui.playList.adapter.PlaylistAdapter
import com.example.erkinbekovbilimdz3_month6.ui.playList.playlistViewModel.PlaylistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlaylistActivity : BaseActivity<ActivityPlaylistBinding, PlaylistViewModel>() {

    override val viewModel: PlaylistViewModel by viewModel()
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
                    adapter = PlaylistAdapter(this::onClick)
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

    private fun onClick(item: Item) {
        val intent = Intent(this@PlaylistActivity, DetailPlaylistActivity::class.java)
        intent.putExtra(KEY_FOR_COUNT, item.contentDetails.itemCount.toString())
        intent.putExtra(KEY_FOR_DESC, item.snippet.description)
        intent.putExtra(
            KEY_FOR_DESC,
            "Популярный русскоязычный YouTube-канал, предлагающий разнообразный контент об играх, автомобилях и развлечениях. Подписчиков привлекает юмор, аутентичность и захватывающие моменты, создаваемые харизматичным ведущим."
        )
        intent.putExtra(KEY_FOR_TITLE, item.snippet.title)
        intent.putExtra(KEY_FOR_ID, item.id)
        startActivity(intent)
    }

    companion object {
        const val KEY_FOR_TITLE = "TITLE"
        const val KEY_FOR_DESC = "DESC"
        const val KEY_FOR_COUNT = "COUNT"
        const val KEY_FOR_ID = "ID"
    }
}