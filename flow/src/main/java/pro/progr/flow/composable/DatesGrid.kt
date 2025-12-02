package pro.progr.flow.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pro.progr.flow.model.CalendarDay
import pro.progr.flow.vm.CalendarViewModel
import java.time.LocalDate

@Composable
fun DatesGrid(calendarViewModel: CalendarViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 7),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {

        items(items = calendarViewModel.calendarPage.value.month.pageDays) { item: CalendarDay? ->

            item?.let {
                if (calendarViewModel.blockTillToday && item.localDate < LocalDate.now()) {
                    TextButton(modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.LightGray), enabled = false, onClick = {}) {
                        Text(
                            text = AnnotatedString(item.dayNumber.toString()),
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                color = if (item.isWeekend) Color.Red else Color.Black,
                                //fontWeight = FontWeight.Bold
                            )
                        )
                    }
                } else if (calendarViewModel.multiSelect) {
                    TextButton(
                        onClick = {
                            //todo: вроде это два разных действия для разных календарей
                            calendarViewModel.updateDayOfMonth()
                            calendarViewModel.selectGridDate(item.localDate)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = if (calendarViewModel.isDateSelected(item.localDate)) Color(0xffDCEDC8) else Color.Transparent)
                    ) {
                        Text(
                            text = AnnotatedString(item.dayNumber.toString()),
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                color = if (item.isWeekend) Color.Red else Color.Black,
                                //fontWeight = FontWeight.Bold
                            )
                        )
                    }
                } else {
                    TextButton(
                        border = if (calendarViewModel.isDateSelected(item.localDate))
                            BorderStroke(width = 1.dp, color = Color.Black)
                        else
                            null,
                        onClick = {
                            //todo: вроде это два разных действия для разных календарей
                            calendarViewModel.updateDayOfMonth()
                            calendarViewModel.selectGridDate(item.localDate)
                        }
                    ) {
                        Text(
                            text = AnnotatedString(item.dayNumber.toString()),
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
}