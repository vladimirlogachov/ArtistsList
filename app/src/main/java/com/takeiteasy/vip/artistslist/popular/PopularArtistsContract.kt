package com.takeiteasy.vip.artistslist.popular

import com.takeiteasy.vip.artistslist.models.Artist

interface PopularArtistsContract {
    interface View {
        fun showProgress(show: Boolean)
        fun showError(error: String)
        fun showArtists(artists: List<Artist>)
    }

    interface Presenter {
        fun setView(view: View?)
        fun loadArtists(country: String)
    }
}