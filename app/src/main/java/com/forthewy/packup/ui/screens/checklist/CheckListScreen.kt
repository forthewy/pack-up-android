package com.forthewy.packup.ui.screens.checklist


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.forthewy.packup.data.local.entity.CheckItem
import com.forthewy.packup.data.model.Category
import com.forthewy.packup.ui.screens.checklist.components.AddItemDialog
import com.forthewy.packup.ui.screens.checklist.components.CategoryTopBar
import com.forthewy.packup.ui.screens.checklist.components.CheckItemCard
import com.forthewy.packup.ui.screens.checklist.components.DeleteItemDialog
import com.forthewy.packup.ui.screens.checklist.components.EditItemDialog

@Composable
fun CheckListScreen(
    category: Category
) {
    val viewModel: CheckListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var editTitle by remember {
        mutableStateOf("")
    }

    var deleteItem by remember {
        mutableStateOf<CheckItem?>(null)
    }
    var editItem by remember {
        mutableStateOf<CheckItem?>(null)
    }
    LaunchedEffect(category) {
        viewModel.load(category)
    }

    Scaffold(
        topBar = {
            CategoryTopBar(
                category = category,
                progress = uiState.progress,
                onBack = {
                    // 뒤로가기
                },
                onAdd = {
                    showDialog = true
                },
                onDeleteAll = {
                    viewModel.deleteAll(category)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "추가"
                )
            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(uiState.items) { item ->

                CheckItemCard(
                    item = item,
                    onCheckedChange = {
                        viewModel.updateItem(
                            item.copy(
                                isChecked = it
                            )
                        )
                    },
                    onEditClick = {
                        editItem = item
                        editTitle = item.title
                    },
                    onDeleteClick = {
                        deleteItem = item
                    }
                )

            }
        }
    }
    if (showDialog) {
        AddItemDialog(
            title = title,
            onTitleChange = {
                title = it
            },
            onAdd = {

                if (title.isBlank()) return@AddItemDialog

                viewModel.addItem(
                    category = category,
                    title = title.trim()
                )

                title = ""
                showDialog = false
            },
            onDismiss = {
                showDialog = false
            }
        )
    }
    if (deleteItem != null) {
        DeleteItemDialog(
            item = deleteItem,
            onDelete = {
                viewModel.deleteItem(deleteItem!!)
                deleteItem = null
            },
            onDismiss = {
                deleteItem = null
            }
        )
    }
    if (editItem != null) {
        EditItemDialog(
            title = editTitle,
            onDismiss = {
                editItem = null
            },
            onSave = {
                if (editTitle.isBlank()) return@EditItemDialog

                viewModel.updateItem(
                    editItem!!.copy(
                        title = editTitle
                    )
                )

                editItem = null
                editTitle = ""
            },
            onTitleChange = {
                editTitle = it
            }
        )
    }
}