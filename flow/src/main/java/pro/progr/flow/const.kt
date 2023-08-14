package pro.progr.flow

import pro.progr.flow.model.DateFlow
import pro.progr.flow.vm.CalendarViewModel
import pro.progr.flow.vm.DatesGridViewModel
import java.time.LocalDate
import java.time.temporal.ChronoUnit

const val CALENDAR_INITIAL_LOAD_SIZE = Integer.MAX_VALUE
const val START_POSITION = CALENDAR_INITIAL_LOAD_SIZE/2

val startDate = LocalDate.now()
val dateFlow = DateFlow()
val calendarViewModel = CalendarViewModel()
val datesGridViewModel = DatesGridViewModel()

//todo: найти startDate и всем этим методам достойное место

fun getIndex(displayDate : LocalDate) : Int {
    return START_POSITION + getDayDiff(displayDate, startDate).toInt()
}

fun getDateByIndex(index : Int) : LocalDate {
    return startDate.plusDays((index - START_POSITION).toLong())
}

fun getDayDiff(date1: LocalDate, date2: LocalDate): Long {
    return ChronoUnit.DAYS.between(date2, date1)
}