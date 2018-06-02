package com.takeiteasy.vip.artistslist.router

import android.content.Context
import android.content.Intent
import com.takeiteasy.vip.artistslist.album.AlbumInfoActivity
import com.takeiteasy.vip.artistslist.artist.ArtistActivity



class ActivityRouter {

    companion object {
        const val ARG_ARTIST_NAME: String = "ARG_ARTIST_NAME"
        const val ARG_ALBUM_NAME: String = "ARG_ALBUM_NAME"
    }

    fun requestArtistActivityIntent(context: Context, artist: String, listener: OnIntentReadyListener) {
        val intent = Intent(context, ArtistActivity::class.java)
        intent.putExtra(ARG_ARTIST_NAME, artist)
        listener.onIntentReady(intent)
    }

    fun requestAlbumInfoActivityIntent(context: Context, artist: String, album: String, listener: OnIntentReadyListener) {
        val intent = Intent(context, AlbumInfoActivity::class.java)
        intent.putExtra(ARG_ARTIST_NAME, artist)
        intent.putExtra(ARG_ALBUM_NAME, album)
        listener.onIntentReady(intent)
    }

    interface OnIntentReadyListener {
        fun onIntentReady(intent: Intent)
    }
}