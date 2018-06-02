package com.takeiteasy.vip.artistslist.models

import com.google.gson.annotations.SerializedName

data class TopArtists(
        val artist: MutableList<Artist>,
        @SerializedName("@attr")
        val attr: GeoArtistsPagingData
)