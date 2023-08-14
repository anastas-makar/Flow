package pro.progr.flow.vm

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import pro.progr.flow.getIndex
import java.time.LocalDate

class DatesGridViewModel {
    private val _selectedIndex = MutableStateFlow(getIndex(LocalDate.now()))
    val selectedIndex : Flow<Int> = _selectedIndex

    fun selectIndex(index : Int) {
        _selectedIndex.value = index
    }

}