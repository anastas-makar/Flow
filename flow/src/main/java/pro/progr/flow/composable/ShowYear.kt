package pro.progr.flow.composable

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import pro.progr.flow.R
import pro.progr.flow.vm.CalendarViewModel

@Composable
fun ShowYear(editYear: MutableState<Boolean>, calendarViewModel: CalendarViewModel) {
    Text(text = calendarViewModel.calendarPage.value.yearName)

    IconButton(
        onClick = {
            editYear.value = !editYear.value
        }
    ) {
        Icon(
            imageVector = Icons.Outlined.Edit,
            contentDescription = stringResource(id = R.string.left)
        )
    }
}