package pro.progr.flow.model

import pro.progr.flow.startDate
import java.time.LocalDate

class CalendarDatesRepository : DatesRepository {

    var selectedDate = startDate

    override fun updateDate(date: LocalDate) {
        selectedDate = date
    }

    override fun isDateSelected(date: LocalDate) : Boolean {
        return selectedDate.isEqual(date)
    }

    override fun getAnchorDate(): LocalDate {
        return selectedDate
    }
}