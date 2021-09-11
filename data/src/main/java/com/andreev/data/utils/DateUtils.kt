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

    fun getStartOfTheDay(date: Date): Date {
        val cal: Calendar = GregorianCalendar()
        cal.time = date
        cal[Calendar.HOUR_OF_DAY] = 0
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0
        cal[Calendar.MILLISECOND] = 0
        return cal.time
    }
}