package pro.progr.flow.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pro.progr.flow.R
import pro.progr.flow.vm.CalendarViewModel

@Composable
fun EditYear(editYear: MutableState<Boolean>, calendarViewModel: CalendarViewModel) {
    val newYear = rememberSaveable { mutableStateOf(calendarViewModel.calendarPage.value.yearName) }

    TextField(
        value = newYear.value,
        onValueChange = { v -> newYear.value = v },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = TextStyle(textAlign = TextAlign.Center),
        modifier = Modifier
            .width(80.dp)
            .padding(bottom = 10.dp),
        colors = TextFieldDefaults.textFieldColors( // Изменяем цвета TextField
            backgroundColor = Color.Transparent
        ),
        singleLine = true, // Запрещаем перенос строк, чтобы текст был в одну строку
        maxLines = 1 // Устанавливаем максимальное количество строк равным 1
    )

    IconButton(
        onClick = {
            calendarViewModel.updateYear(newYear.value)
            editYear.value = !editYear.value
        }
    ) {
        Icon(
            imageVector = Icons.Outlined.Done,
            contentDescription = stringResource(id = R.string.left)
        )
    }
}