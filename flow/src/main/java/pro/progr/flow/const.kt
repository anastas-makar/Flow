package pro.progr.flow

import pro.progr.flow.model.CalendarDatesRepository
import pro.progr.flow.model.DateFlow
import pro.progr.flow.model.Index
import pro.progr.flow.vm.CalendarViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

const val CALENDAR_INITIAL_LOAD_SIZE = Integer.MAX_VALUE
const val START_POSITION = CALENDAR_INITIAL_LOAD_SIZE/2

val startDate = LocalDate.now()
val dateFlow = DateFlow()
val calendarViewModel = CalendarViewModel(CalendarDatesRepository())

//todo: найти startDate и всем этим методам достойное место

fun getIndex(displayDate : LocalDate) : Index {
    return Index(index = START_POSITION + getDayDiff(displayDate, startDate).toInt(), updated = LocalDateTime.now())
}

fun getDateByIndex(index : Int) : LocalDate {
    return startDate.plusDays((index - START_POSITION).toLong())
}

fun getDayDiff(date1: LocalDate, date2: LocalDate): Long {
    return ChronoUnit.DAYS.between(date2, date1)
}