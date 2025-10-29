package my.id.zaxx.news.util

import android.annotation.SuppressLint
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


class HelperConvertor() {

    @SuppressLint("NewApi")
    fun convertDayMonthYear(date: String): String {
        val offsetDateTime = OffsetDateTime.parse(date)

        val outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy")
        val outPutDateString = offsetDateTime.format(outputFormat)

        return outPutDateString.toString()
    }
}