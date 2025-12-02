package pro.progr.flow.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pro.progr.flow.R
import pro.progr.flow.vm.CalendarViewModel

@Composable
fun BoxScope.BottomCalendar(horizontalScrollState: LazyListState,
                   verticalScrollState: LazyListState,
                            calendarViewModel: CalendarViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(0.dp)
            .align(Alignment.BottomCenter)
            .background(Color.White)
            .border(
                width = 2.dp,
                color = Color.LightGray,/*colorResource(id = R.color.opaque_grey),*/
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
            ).pointerInput(Unit) {
            detectTapGestures(
                onPress = { /* обработка нажатия, но не передача его дальше */ }
            )
        },
    ) {
        val isGridCalendarVisible = remember { mutableStateOf(false) }

        Box {
            HorizontalDatesList(horizontalScrollState, verticalScrollState)

            IconButton(
                onClick = { isGridCalendarVisible.value = !isGridCalendarVisible.value },
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
                    .wrapContentSize()
            ) {
                Icon(
                    imageVector = if (isGridCalendarVisible.value) {
                        Icons.Outlined.KeyboardArrowDown
                    } else {
                        Icons.Outlined.KeyboardArrowUp
                    },
                    contentDescription = stringResource(id = R.string.up)
                )
            }

        }

        if (isGridCalendarVisible.value) {
            GridCalendar(calendarViewModel)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(2.dp)
            .align(Alignment.BottomCenter)
    )
}