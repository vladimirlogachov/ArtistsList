package com.takeiteasy.vip.artistslist.models

data class AlbumsPagingData(
        val artist: String,
        val page: Int,
        val perPage: Int,
        val totalPages: Int,
        val total: Int
)