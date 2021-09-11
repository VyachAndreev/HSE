package com.andreev.data.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private const val patternSdf = "HH:mm"
    private const val patternSdfDay = "yyyy-MM-dd"
    private const val patternSdfDayTime = "yyyy-MM-dd HH:mm"
    private val locale = Locale("ru")
    val sdf = SimpleDateFormat(patternSdf, locale)
    val sdfDay = SimpleDateFormat(patternSdfDay, locale)
    val sdfDayTime = SimpleDateFormat(patternSdfDayTime, locale)

    fun formatSimpleDate(date: Date?): String? =
        if (date != null) sdf.format(date) else null

    fun formatSimpleDateDay(date: Date?): String? =
        if (date != null) sdfDay.format(date) else null

    fun formatSimpleDateDayTime(date: Date?): String? =
        if (date != null) sdfDayTime.format(date) else null

    fun parseDate(string: String?): Date? =
        if (string != null) sdfDayTime.parse(string) else null
}