package pro.progr.flow.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.zIndex
import pro.progr.flow.START_POSITION
import pro.progr.flow.calendarViewModel
import pro.progr.flow.getDateByIndex
import pro.progr.flow.getIndex
import pro.progr.flow.util.Scroller
import pro.progr.flow.vm.FloatingElementViewModel
import java.time.LocalDate

//todo: общие куски кода вынести отдельно
@Composable
fun CalendarWithFloatingElement(content: @Composable (date : LocalDate) -> Unit,
                                floatingElement: @Composable () -> Unit,
                                floatingElementViewModel: FloatingElementViewModel
) {

    val verticalScrollState = rememberLazyListState(initialFirstVisibleItemIndex = START_POSITION)
    val horizontalScrollState = rememberLazyListState(initialFirstVisibleItemIndex = START_POSITION)

    val index = calendarViewModel.selectedGridIndex.collectAsState(initial = getIndex(calendarViewModel.getSelectedDate()))
    Scroller.scroll(verticalScrollState, horizontalScrollState, index.value)


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

        AnimatedVisibility(visible = floatingElementViewModel.showScrim.value, enter = fadeIn(), exit = fadeOut()) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xEEFFFFFF))
                .zIndex(1f)
                .clickable {
                    floatingElementViewModel.showScrim.value = false
                }
            )
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter).zIndex(2f)
        ) {
            floatingElement()

            Box {
                BottomCalendar(
                    horizontalScrollState = horizontalScrollState,
                    verticalScrollState = verticalScrollState,
                    calendarViewModel
                )
            }

        }

    }
}