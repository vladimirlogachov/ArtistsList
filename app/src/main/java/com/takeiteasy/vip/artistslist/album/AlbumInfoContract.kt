package com.takeiteasy.vip.artistslist.album

import com.takeiteasy.vip.artistslist.models.AlbumInfo

interface AlbumInfoContract {
    interface View {
        fun showProgress(show: Boolean)
        fun showError(error: String)
        fun showAlbumInfo(albumInfo: AlbumInfo)
    }

    interface Presenter{
        fun setView(view: View?)
        fun loadAlbumInfo(artist:String, album:String)
    }
}