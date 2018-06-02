package com.takeiteasy.vip.artistslist.album

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.takeiteasy.vip.artistslist.R
import com.takeiteasy.vip.artistslist.models.Track
import com.takeiteasy.vip.artistslist.utils.TimeUtils
import kotlinx.android.synthetic.main.item_track.view.*

class TracksAdapter : RecyclerView.Adapter<TracksAdapter.ViewHolder>() {

    private val data: MutableList<Track> = mutableListOf()

    fun updateData(data: List<Track>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflateView(parent, R.layout.item_track))
    }

    private fun inflateView(parent: ViewGroup, @LayoutRes layoutRes: Int): View {
        return LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(track: Track) = with(itemView) {
            order.text = (adapterPosition + 1).toString()
            name.text = track.name
            duration.text = TimeUtils.formatDuration(track.duration)
        }
    }
}