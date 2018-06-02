package com.takeiteasy.vip.artistslist.models

import android.support.annotation.StringDef
import com.google.gson.annotations.SerializedName

data class Image(
        val size: String,
        @SerializedName("#text")
        val url: String
) {
        @Retention(AnnotationRetention.SOURCE)
        @StringDef(SMALL, MEDIUM, LARGE, EXTRA_LARGE, MEGA)
        annotation class ImageSize

        companion object {
                const val SMALL: String = "small"
                const val MEDIUM: String = "medium"
                const val LARGE: String = "large"
                const val EXTRA_LARGE: String = "extralarge"
                const val MEGA: String = "mega"
        }
}