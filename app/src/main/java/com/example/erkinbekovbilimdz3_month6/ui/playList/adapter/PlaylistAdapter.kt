package com.example.erkinbekovbilimdz3_month6.ui.playList.adapter

import android.annotation.SuppressLint
import com.example.erkinbekovbilimdz3_month6.data.model.PlaylistModel.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.erkinbekovbilimdz3_month6.databinding.ItemPlaylistBinding
import com.example.erkinbekovbilimdz3_month6.utils.loadImage

class PlaylistAdapter(private val onClick: (Item) -> Unit) :
    Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private var data = ArrayList<Item>()

    fun addList(list: List<Item>) {
        val previousSize = data.size
        data.clear()
        data.addAll(list)
        val newSize = list.size
        if (previousSize == newSize) notifyItemRangeChanged(0, newSize)
        else notifyItemRangeInserted(0, newSize)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class PlaylistViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {
            binding.apply {
                tvVideoTitle.text = item.snippet.title
                tvCountVideo.text = item.contentDetails.itemCount.toString() + " video series"
                imgPreview.loadImage(item.snippet.thumbnails.high.url)
            }
            itemView.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }

}