package pro.progr.flow.composable

import androidx.compose.runtime.Composable
import java.time.LocalDate


@Composable
fun CalendarListItem(date : LocalDate, content: @Composable (date : LocalDate) -> Unit) {

    content(date)

}