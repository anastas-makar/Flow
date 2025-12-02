package pro.progr.flow.model

import java.time.LocalDate

interface DatesRepository {

    fun updateDate(date : LocalDate)

    fun isDateSelected(date: LocalDate) : Boolean

    fun getAnchorDate() : LocalDate
}