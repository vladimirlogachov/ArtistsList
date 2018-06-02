package com.takeiteasy.vip.artistslist.models

data class GeoArtistsPagingData(
        val country: String,
        val page: Int,
        val perPage: Int,
        val totalPages: Int,
        val total: Int
)