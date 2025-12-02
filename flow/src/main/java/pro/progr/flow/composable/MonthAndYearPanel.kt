package pro.progr.flow.composable

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pro.progr.flow.vm.CalendarViewModel

@Composable
fun MonthAndYearPanel(calendarViewModel: CalendarViewModel) {
    val editYear = remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {

        ChangeMonth(calendarViewModel)

        if (editYear.value) {
            EditYear(editYear = editYear, calendarViewModel = calendarViewModel)
        } else {
            ShowYear(editYear = editYear, calendarViewModel = calendarViewModel)
        }

        Spacer(modifier = Modifier.width(8.dp))

    }
}