package pro.progr.flow.model

import java.time.LocalDate

class CalendarDay(
    //todo: если передаю localDate, dayNumber и dayOfWeek не нужно передавать
    val localDate: LocalDate,
    val dayNumber: Int,
    val dayOfWeek: Int,
    val selected : Boolean = false) {
    val isWeekend : Boolean
        get() {
            return (dayOfWeek > 5)
        }
}