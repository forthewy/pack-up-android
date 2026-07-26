package com.forthewy.packup.ui.screens.checklist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.forthewy.packup.data.model.Category
import com.forthewy.packup.ui.extensions.titleRes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryTopBar(
    category: Category,
    progress: Int,
    onBack: () -> Unit,
    onAdd: () -> Unit,
    onDeleteAll: () -> Unit,
) {
    Column {
        TopAppBar(
            title = {
                Column {
                    Text(stringResource(category.titleRes()))
                    Text(
                        text = "$progress%",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "뒤로"
                    )
                }
            },
            actions = {
                TextButton(onClick = onDeleteAll) {
                    Text("전체 삭제")
                }

                IconButton(onClick = onAdd) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "추가"
                    )
                }
            }
        )
    }
}