package pro.progr.flow.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import pro.progr.flow.getIndex
import pro.progr.flow.model.CalendarPage
import pro.progr.flow.model.DatesRepository
import pro.progr.flow.model.Index
import pro.progr.flow.startDate
import java.time.LocalDate

open class CalendarViewModel(val repository: DatesRepository) : ViewModel() {

    private var displayDate = startDate

    val calendarPage = mutableStateOf(CalendarPage(displayDate, repository.getAnchorDate()))

    fun monthBefore() {
        displayDate = displayDate.minusMonths(1)
        calendarPage.value = CalendarPage(displayDate, repository.getAnchorDate())
    }

    fun monthAfter() {
        displayDate = displayDate.plusMonths(1)
        calendarPage.value = CalendarPage(displayDate, repository.getAnchorDate())
    }

    fun updateDayOfMonth() {
        calendarPage.value = CalendarPage(displayDate, repository.getAnchorDate())
    }

    fun updateSelectedDate(date : LocalDate) {
        repository.updateDate(date)
        calendarPage.value = CalendarPage(displayDate, repository.getAnchorDate())
    }

    fun updateYear(yearName: String) {
        displayDate = displayDate.withYear(yearName.toInt())
        calendarPage.value = CalendarPage(displayDate, repository.getAnchorDate())
    }

    private val _selectedGridIndex = MutableStateFlow(getIndex(LocalDate.now()))
    val selectedGridIndex : Flow<Index> = _selectedGridIndex

    open fun selectGridDate(date : LocalDate) {
        _selectedGridIndex.value = getIndex(date)
        repository.updateDate(date)
    }

    open fun getSelectedDate() : LocalDate {
        return repository.getAnchorDate()
    }

    open fun isDateSelected(date : LocalDate) = repository.isDateSelected(date)

    open var blockTillToday = false

    open var multiSelect = false
}