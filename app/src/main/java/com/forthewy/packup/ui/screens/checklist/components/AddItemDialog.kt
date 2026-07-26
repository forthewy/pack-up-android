package com.forthewy.packup.ui.screens.checklist.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AddItemDialog(
    title: String,
    onTitleChange: (String) -> Unit,
    onAdd: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("항목 추가")
        },
        text = {
            OutlinedTextField(
                value = title,
                onValueChange = onTitleChange,
                label = {
                    Text("항목")
                }
            )
        },
        confirmButton = {
            TextButton(
                onClick = onAdd
            ) {
                Text("추가")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("취소")
            }
        }
    )
}