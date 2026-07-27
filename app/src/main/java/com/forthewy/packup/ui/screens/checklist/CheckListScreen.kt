package com.forthewy.packup.ui.screens.checklist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.forthewy.packup.data.local.entity.CheckItem
import com.forthewy.packup.data.model.Category
import com.forthewy.packup.ui.screens.checklist.components.AddItemDialog
import com.forthewy.packup.ui.screens.checklist.components.CategoryTopBar
import com.forthewy.packup.ui.screens.checklist.components.ChildItemRow
import com.forthewy.packup.ui.screens.checklist.components.DeleteItemDialog
import com.forthewy.packup.ui.screens.checklist.components.EditItemDialog
import com.forthewy.packup.ui.screens.checklist.components.EmptyChecklist
import com.forthewy.packup.ui.screens.checklist.components.ParentItemCard
import com.forthewy.packup.ui.screens.checklist.components.SuggestionDialog

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
    var showSuggestionDialog by remember { mutableStateOf(false) }
    val expandedIds = remember {
        mutableStateListOf<Int>()
    }
    val parents = uiState.items.filter { it.parentId == null }
    var parentId by remember {
        mutableStateOf<Int?>(null)
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
                    parentId = null
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
                    parentId = null
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
        if (uiState.items.isEmpty()) {
            EmptyChecklist(
                onSuggestionClick = {
                    showSuggestionDialog = true
                }
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(parents) { parent ->
                    val children = uiState.items.filter {
                        it.parentId == parent.id
                    }

                    val expanded = parent.id in expandedIds

                    ParentItemCard(
                        parent = parent,
                        children = children,
                        expanded = expanded,

                        onCheckedChange = {
                            viewModel.updateItem(parent.copy(isChecked = it))
                        },

                        onEditClick = {
                            editItem = parent
                            editTitle = parent.title
                        },

                        onDeleteClick = {
                            deleteItem = parent
                        },

                        onAddChildClick = {
                            parentId = parent.id
                            showDialog = true
                        },

                        onExpandClick = {
                            if (expanded)
                                expandedIds.remove(parent.id)
                            else
                                expandedIds.add(parent.id)
                        },

                        onChildCheckedChange = { child, checked ->
                            viewModel.updateItem(child.copy(isChecked = checked))
                        },

                        onChildEditClick = { child ->
                            editItem = child
                            editTitle = child.title
                        },

                        onChildDeleteClick = { child ->
                            deleteItem = child
                        }
                    )
                }
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
                    title = title.trim(),
                    parentId = parentId
                )

                title = ""
                showDialog = false
                parentId = null
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
    if (showSuggestionDialog) {
        SuggestionDialog(
            category = category,
            onDismiss = { showSuggestionDialog = false },
            onAdd = { items ->
                viewModel.addCheckedSuggestedItems(category, items)
                showSuggestionDialog = false
            }
        )
    }
}