package ru.sanchozgamesstore.android.utils.converter

import android.util.Log
import ru.sanchozgamesstore.android.utils.DEFAULT_DATE
import ru.sanchozgamesstore.android.utils.DEFAULT_DATE_TIME
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object DateConverter {
    private const val TAG = "DateConverter"

    const val DISPLAY_PATTERN_DATE = "dd.MM.yyyy"
    const val DISPLAY_PATTERN_DATE_TIME = "dd.MM.yyyy HH:mm"

    private val formatterBackendDate: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    private val formatterDisplayDate: DateTimeFormatter =
        DateTimeFormatter.ofPattern(DISPLAY_PATTERN_DATE)

    private val formatterBackendDateTime: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
    private val formatterDisplayDateTime: DateTimeFormatter =
        DateTimeFormatter.ofPattern(DISPLAY_PATTERN_DATE_TIME)


    fun backendDateToDisplayDate(date: String?): String {
        return if (date.isNullOrEmpty()) {
            DEFAULT_DATE
        } else {
            try {
                val backendDate = LocalDate.parse(date, formatterBackendDate)
                val displayDate = backendDate.format(formatterDisplayDate)

                displayDate
            } catch (e: DateTimeParseException) {
                Log.e(TAG, "EXCEPTION: ${e.message}")
                DEFAULT_DATE_TIME
            }
        }
    }

    fun backendDateTimeToDisplayDateTime(dateTime: String?): String {
        return if (dateTime.isNullOrEmpty()) {
            DEFAULT_DATE_TIME
        } else {
            try {
                val backendDateTime = LocalDateTime.parse(dateTime, formatterBackendDateTime)
                val displayDateTime = backendDateTime.format(formatterDisplayDateTime)

                displayDateTime
            } catch (e: DateTimeParseException) {
                Log.e(TAG, "EXCEPTION: ${e.message}")
                DEFAULT_DATE_TIME
            }

        }
    }
}