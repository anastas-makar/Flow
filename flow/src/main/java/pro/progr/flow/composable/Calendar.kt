package pro.progr.flow.composable

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.unit.Velocity
import pro.progr.flow.START_POSITION
import pro.progr.flow.calendarViewModel
import pro.progr.flow.getDateByIndex
import java.time.LocalDate

@Composable
fun Calendar(content: @Composable (date : LocalDate) -> Unit) {

    val verticalScrollState = rememberLazyListState(initialFirstVisibleItemIndex = START_POSITION)
    val horizontalScrollState = rememberLazyListState(initialFirstVisibleItemIndex = START_POSITION)

    val nestedScrollConnectionVert = remember {
        object : NestedScrollConnection {
            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                horizontalScrollState.scrollToItem(verticalScrollState.firstVisibleItemIndex)
                calendarViewModel.updateSelectedDate(getDateByIndex(verticalScrollState.firstVisibleItemIndex))
                return super.onPostFling(consumed, available)
            }
        }
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
    ) {
        VerticalDatesList(nestedScrollConnectionVert, verticalScrollState, content)

        BottomCalendar(
            horizontalScrollState = horizontalScrollState,
            verticalScrollState = verticalScrollState
        )

    }
}