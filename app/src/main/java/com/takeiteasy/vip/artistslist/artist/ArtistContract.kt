package com.takeiteasy.vip.artistslist.artist

import com.takeiteasy.vip.artistslist.models.Album

interface ArtistContract {
    interface View {
        fun showProgress(show: Boolean)
        fun showError(error: String)
        fun showTopAlbums(topAlbums: List<Album>)
    }

    interface Presenter {
        fun setView(view: View?)
        fun loadArtistTopAlbums(artist: String)
    }
}