package ru.sanchozgamesstore

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.sanchozgamesstore.android.utils.converter.DateConverter

class DateConverterTest {
    @Test
    fun test_backendDateToDisplayDate() {
        val backend_ISO_DATE = "2013-09-17"
        val expected_DISPLAY_DATE = "17.09.2013"

        val res = DateConverter.backendDateToDisplayDate(backend_ISO_DATE)

        assertEquals(expected_DISPLAY_DATE, res)
    }

    @Test
    fun test_backendDateTimeToDisplayDateTime() {
        val backend_ISO_DATE_TIME = "2023-01-06T09:07:02"
        val expected_DISPLAY_DATE_TIME = "06.01.2023 09:07"

        val res = DateConverter.backendDateTimeToDisplayDateTime(backend_ISO_DATE_TIME)

        assertEquals(expected_DISPLAY_DATE_TIME, res)
    }
}