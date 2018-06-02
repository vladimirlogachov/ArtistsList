package com.takeiteasy.vip.artistslist.models

data class AlbumInfo(
        val name: String,
        val artist: String,
        val mbid: String,
        val url: String,
        val image: List<Image>,
        val listeners: Int,
        val playcount: Int,
        val tracks: Tracks
) {
    fun getLargeImageUrl(): String? {
        return findImage(Image.LARGE)?.url
    }

    private fun findImage(@Image.ImageSize size: String): Image? {
        return image.find { item -> item.size == size }
    }
}