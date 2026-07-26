package com.forthewy.packup.ui.screens.category

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.forthewy.packup.data.model.Category
import com.forthewy.packup.ui.screens.category.components.CategoryCard

@Composable
fun CategoryScreen(
    onCategoryClick: (Category) -> Unit,
    viewModel: CategoryViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ) {
        items(uiState.categories) { item ->

            CategoryCard(
                title = item.category.name,
                itemCount = item.itemCount,
                progress = item.progress,
                checkedCount = item.checkedCount,
                        onClick = {
                    onCategoryClick(item.category)
                }
            )
        }
    }
}