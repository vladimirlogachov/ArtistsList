package com.takeiteasy.vip.artistslist.utils

import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {
    companion object {
        @JvmField val DURATION_FORMATTER: SimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        fun formatDuration(duration: Int): String {
            val c = Calendar.getInstance()
            c[Calendar.HOUR_OF_DAY] = 0
            c[Calendar.MINUTE] = 0
            c[Calendar.SECOND] = 0
            c.add(Calendar.SECOND, duration)
            return DURATION_FORMATTER.format(c.time)
        }
    }
}