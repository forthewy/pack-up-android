package com.forthewy.packup.ui.screens.checklist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.forthewy.packup.data.local.entity.CheckItem
import com.forthewy.packup.ui.theme.CategoryText
import com.forthewy.packup.ui.theme.ChecklistCardBackground
import com.forthewy.packup.ui.theme.ChecklistCheckedText
import com.forthewy.packup.ui.theme.ChecklistDivider
import com.forthewy.packup.ui.theme.ChecklistIcon
import com.forthewy.packup.ui.theme.DeleteColor

@Composable
fun ParentItemCard(
    parent: CheckItem,
    children: List<CheckItem>,
    expanded: Boolean,

    onCheckedChange: (Boolean) -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onAddChildClick: () -> Unit,
    onExpandClick: () -> Unit,

    onChildCheckedChange: (CheckItem, Boolean) -> Unit,
    onChildEditClick: (CheckItem) -> Unit,
    onChildDeleteClick: (CheckItem) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = ChecklistCardBackground
        )
    ) {

        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = parent.isChecked,
                    onCheckedChange = onCheckedChange
                )
                Column {
                    Text(
                        text = parent.title,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (parent.isChecked)
                            ChecklistCheckedText
                        else
                            CategoryText,
                        textDecoration = if (parent.isChecked)
                            TextDecoration.LineThrough
                        else
                            TextDecoration.None
                    )
                }

                Spacer(Modifier.weight(1f))

                IconButton(
                    modifier = Modifier.size(38.dp),
                    onClick = onEditClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        tint = ChecklistIcon,
                        contentDescription = null
                    )
                }

                IconButton(
                    modifier = Modifier.size(38.dp),
                    onClick = onAddChildClick
                ) {
                    Icon(Icons.Default.Add, null)
                }

                IconButton(
                    modifier = Modifier.size(38.dp),
                    onClick = onDeleteClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        tint = DeleteColor,
                        contentDescription = null
                    )
                }
                if (children.isNotEmpty()) {
                    IconButton(
                        modifier = Modifier.size(38.dp),
                        onClick = onExpandClick
                    ) {
                        Icon(
                            imageVector = if (expanded)
                                Icons.Default.ExpandLess
                            else
                                Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = ChecklistIcon
                        )
                    }
                }
            }
            if (expanded && children.isNotEmpty()) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = ChecklistDivider
                )
                Column(
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                ) {
                    children.forEach { child ->

                        ChildItemRow(
                            item = child,
                            onCheckedChange = {
                                onChildCheckedChange(child, it)
                            },
                            onEditClick = {
                                onChildEditClick(child)
                            },
                            onDeleteClick = {
                                onChildDeleteClick(child)
                            }
                        )
                    }
                }
            }
        }
    }
}