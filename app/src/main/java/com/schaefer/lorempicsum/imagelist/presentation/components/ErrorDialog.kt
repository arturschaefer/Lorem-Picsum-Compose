package com.schaefer.lorempicsum.imagelist.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import com.schaefer.lorempicsum.R

@Composable
fun ErrorDialog(message: String, onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = stringResource(R.string.problem_occurred))
        },
        text = {
            Text(message)
        },
        confirmButton = {
            Button(shape = RectangleShape, onClick = onDismissRequest) {
                Text(text = "Ok")
            }
        }
    )
}
