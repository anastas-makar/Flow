package pro.progr.flow.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GridCalendar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(0.dp)
            .background(Color.White),
    ) {
        MonthAndYearPanel()
        DaysOfWeekPanel()
        DatesGrid()
    }
}