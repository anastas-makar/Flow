package pro.progr.flow.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

class CalendarMonth(displayDate: LocalDate, private val selectedDate : LocalDate) {

    val monthName: String
    val pageDays: MutableList<CalendarDay?>
    private var calendarDate: LocalDate

    init {
        calendarDate = displayDate.withDayOfMonth(1)

        monthName = calendarDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault())

        pageDays = getMonthDays()
    }

    private fun getEmptyDatesSize() : Int {
        return calendarDate.dayOfWeek.value - DayOfWeek.MONDAY.value
    }

    private fun getMonthDays() : MutableList<CalendarDay?> {
        val daysOnPage: MutableList<CalendarDay?> = Collections.nCopies(getEmptyDatesSize(), null).toMutableList()

        val maxDaysInMonth = calendarDate.month.length(calendarDate.isLeapYear)

        for (dayNum in 1..maxDaysInMonth) {
            daysOnPage.add(
                CalendarDay(
                    localDate = calendarDate,
                    dayNumber = calendarDate.dayOfMonth,
                    dayOfWeek = calendarDate.dayOfWeek.value,
                    selected = calendarDate.isEqual(selectedDate)
                ))
            calendarDate = calendarDate.plusDays(1)
        }

        return daysOnPage
    }
}