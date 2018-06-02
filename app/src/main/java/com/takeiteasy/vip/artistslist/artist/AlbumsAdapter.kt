package com.takeiteasy.vip.artistslist.artist

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.takeiteasy.vip.artistslist.R
import com.takeiteasy.vip.artistslist.models.Album
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumsAdapter(
        private val listener: (String) -> Unit
) : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    private val data: MutableList<Album> = mutableListOf()

    fun updateData(data: List<Album>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflateView(parent, R.layout.item_album))
    }

    private fun inflateView(parent: ViewGroup, @LayoutRes layoutRes: Int): View {
        return LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], listener)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(album: Album, listener: (String) -> Unit) = with(itemView) {
            Glide.with(itemView).load(album.getLargeImageUrl()).into(image)
            name.text = album.name
            playCount.text = context.getString(R.string.play_count_pattern, album.playcount)
            setOnClickListener { listener(album.name) }
        }
    }
}