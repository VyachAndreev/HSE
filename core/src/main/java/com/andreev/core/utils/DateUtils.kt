package com.andreev.core.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())

    fun formatSimpleDate(date: Date?): String? =
        if (date != null) sdf.format(date) else null
}