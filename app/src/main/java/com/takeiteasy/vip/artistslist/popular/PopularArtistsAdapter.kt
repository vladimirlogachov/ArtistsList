package com.takeiteasy.vip.artistslist.popular

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.takeiteasy.vip.artistslist.R
import com.takeiteasy.vip.artistslist.models.Artist
import kotlinx.android.synthetic.main.item_artists.view.*

class PopularArtistsAdapter(
        private val listener: (String) -> Unit
) : RecyclerView.Adapter<PopularArtistsAdapter.ViewHolder>() {

    private val data: MutableList<Artist> = mutableListOf()

    fun updateData(data: List<Artist>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflateView(parent, R.layout.item_artists))
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
        fun bind(artist: Artist, listener: (String) -> Unit) = with(itemView) {
            Glide.with(itemView).load(artist.getLargeImageUrl()).into(image)
            name.text = artist.name
            listeners.text = context.getString(R.string.listeners_pattern, artist.listeners)
            setOnClickListener{listener(artist.name)}
        }
    }
}