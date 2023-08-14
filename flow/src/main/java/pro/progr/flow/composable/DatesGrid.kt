package pro.progr.flow.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pro.progr.flow.calendarViewModel
import pro.progr.flow.datesGridViewModel
import pro.progr.flow.getIndex
import pro.progr.flow.model.CalendarDay

@Composable
fun DatesGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 7),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {

        items(items = calendarViewModel.calendarPage.value.month.pageDays) { item: CalendarDay? ->

            item?.let {
                TextButton(
                    border = if (item.selected)
                                BorderStroke(width = 1.dp, color = Color.Black)
                            else
                                null,
                    onClick = {
                        calendarViewModel.updateDayOfMonth(item.dayNumber)
                        datesGridViewModel.selectIndex(getIndex(item.localDate))
                    }
                ) {
                    Text(text = AnnotatedString(item.dayNumber.toString()),
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            color = if (item.isWeekend) Color.Red else Color.Black,
                            //fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}