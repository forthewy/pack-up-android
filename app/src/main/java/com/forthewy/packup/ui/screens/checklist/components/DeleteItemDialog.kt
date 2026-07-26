package com.forthewy.packup.ui.screens.checklist.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.forthewy.packup.data.local.entity.CheckItem

@Composable
fun DeleteItemDialog(
    item: CheckItem?,
    onDelete: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("삭제")
        },
        text = {
            Text("정말 삭제하시겠습니까?")
        },
        confirmButton = {
            TextButton(
                onClick = onDelete
            ) {
                Text("삭제")
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