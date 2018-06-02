package com.takeiteasy.vip.artistslist.models

data class Track(
        val name: String,
        val url: String,
        val duration: Int,
        val artist: Artist
)