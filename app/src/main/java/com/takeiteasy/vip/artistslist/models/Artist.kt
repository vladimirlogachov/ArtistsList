package com.takeiteasy.vip.artistslist.models

data class Artist(
        val name: String,
        val listeners: Long,
        val mbid: String,
        val url: String,
        val streamable: Int,
        val image: List<Image>
) {
    fun getLargeImageUrl(): String? {
        return findImage(Image.LARGE)?.url
    }

    private fun findImage(@Image.ImageSize size: String): Image? {
        return image.find { item -> item.size == size }
    }
}
