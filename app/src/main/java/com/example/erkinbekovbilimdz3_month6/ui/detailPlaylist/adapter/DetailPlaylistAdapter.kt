package com.example.erkinbekovbilimdz3_month6.ui.detailPlaylist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.erkinbekovbilimdz3_month6.databinding.ItemDetailPlaylistBinding
import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel.Item
import com.example.erkinbekovbilimdz3_month6.utils.loadImage

class DetailPlaylistAdapter : RecyclerView.Adapter<DetailPlaylistAdapter.DetailsViewHolder>() {
    private var data = ArrayList<Item>()

    fun setList(list: List<Item>) {
        val previousSize = list.size
        data.clear()
        data.addAll(list)
        val newSize = list.size
        if (previousSize == newSize) notifyItemRangeChanged(0, newSize)
        else notifyItemRangeInserted(0, newSize)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val binding =
            ItemDetailPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class DetailsViewHolder(private val binding: ItemDetailPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {
            with(binding) {
                tvVideoTitleDetail.text = item.snippet.title
                tvTimerVideo.text = "54:24"
                imgPreviewDetail.loadImage(item.snippet.thumbnails.high.url)
            }
        }
    }
}