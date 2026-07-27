package com.forthewy.packup.ui.screens.checklist.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.forthewy.packup.data.local.entity.CheckItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.style.TextDecoration
import com.forthewy.packup.ui.theme.CategoryText
import com.forthewy.packup.ui.theme.ChecklistCheckedText
import com.forthewy.packup.ui.theme.ChecklistIcon
import com.forthewy.packup.ui.theme.DeleteColor

@Composable
fun ChildItemRow(
    item: CheckItem,
    onCheckedChange: (Boolean) -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 56.dp,
                end = 20.dp,
                top = 6.dp,
                bottom = 6.dp
            )
    ) {

        Checkbox(
            modifier = Modifier.size(20.dp),
            checked = item.isChecked,
            onCheckedChange = onCheckedChange
        )
        Spacer(Modifier.width(12.dp))

        Text(
            text = item.title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge,
            color = if (item.isChecked)
                ChecklistCheckedText
            else
                CategoryText,
            textDecoration = if (item.isChecked)
                TextDecoration.LineThrough
            else
                TextDecoration.None
        )

        IconButton(onClick = onEditClick) {
            Icon(
                Icons.Default.Edit,
                contentDescription = null,
                tint = ChecklistIcon
            )
        }

        IconButton(onClick = onDeleteClick) {
            Icon(
                Icons.Default.Delete,
                contentDescription = null,
                tint = DeleteColor
            )
        }
    }
}