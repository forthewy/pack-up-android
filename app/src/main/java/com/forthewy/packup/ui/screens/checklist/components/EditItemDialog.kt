package com.forthewy.packup.ui.screens.checklist.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun EditItemDialog(
    title: String,
    onTitleChange: (String) -> Unit,
    onSave: () -> Unit,
    onDismiss: () -> Unit
) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text("수정")
            },
            text = {
                OutlinedTextField(
                    value = title,
                    onValueChange = onTitleChange
                )
            },
            confirmButton = {
                TextButton(
                    onClick =
                        onSave

                ) {
                    Text("저장")
                }
            },
            dismissButton = {

                TextButton(
                    onClick = onDismiss
                ) {
                    Text("취소")
                }
            },
        )
    }