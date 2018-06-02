package com.takeiteasy.vip.artistslist.models

data class Album(
        val name: String,
        val playcount: Int,
        val mbid: String,
        val url: String,
        val artist: Artist,
        val image: List<Image>
) {
    fun getLargeImageUrl(): String? {
        return findImage(Image.LARGE)?.url
    }

    private fun findImage(@Image.ImageSize size: String): Image? {
        return image.find { item -> item.size == size }
    }
}