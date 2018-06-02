package com.takeiteasy.vip.artistslist.models

import com.google.gson.annotations.SerializedName

data class TopAlbums(
        val album: List<Album>,
        @SerializedName("@attr")
        val attr: AlbumsPagingData
)