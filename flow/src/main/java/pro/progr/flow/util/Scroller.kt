package pro.progr.flow.util

import androidx.compose.foundation.lazy.LazyListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import pro.progr.flow.model.Index

class Scroller {

    companion object {

        val scrollParentJob = Job()
        val scrollCoroutineScope = CoroutineScope(Dispatchers.Main + scrollParentJob)

        fun scroll(verticalScrollState: LazyListState, horizontalScrollState: LazyListState, index : Index) {

            scrollCoroutineScope.launch {
                verticalScrollState.scrollToItem(index.index)
                horizontalScrollState.scrollToItem(index.index)
            }
        }
    }

}