package pro.progr.flow.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pro.progr.flow.R
import pro.progr.flow.vm.CalendarViewModel

@Composable
fun ChangeMonth(calendarViewModel: CalendarViewModel) {
    IconButton(
        onClick = { calendarViewModel.monthBefore() }
    ) {
        Icon(
            Icons.Outlined.KeyboardArrowLeft,
            contentDescription = stringResource(id = R.string.left)
        )
    }

    Text(
        modifier = Modifier.wrapContentWidth(),
        text = calendarViewModel.calendarPage.value.month.monthName,
        textAlign = TextAlign.Center
    )

    IconButton(
        onClick = { calendarViewModel.monthAfter() }
    ) {
        Icon(
            Icons.Outlined.KeyboardArrowRight,
            contentDescription = stringResource(id = R.string.right)
        )
    }

    Spacer(modifier = Modifier.width(16.dp))
}