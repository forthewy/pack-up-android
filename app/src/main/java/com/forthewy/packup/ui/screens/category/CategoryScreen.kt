package com.forthewy.packup.ui.screens.category

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.forthewy.packup.data.model.Category
import com.forthewy.packup.ui.screens.category.components.CategoryCard
import com.forthewy.packup.ui.screens.category.components.CategoryGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    onCategoryClick: (Category) -> Unit,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Pack Up!")
                }
            )
        },
        containerColor = Color(0xFFF7F8FA)
    ) {  padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                CategoryGrid(
                    onCategoryClick = onCategoryClick
                )
            }
            items(uiState.categories) { item ->
                CategoryCard(
                    categoryItem = item,
                    onClick = {
                        onCategoryClick(item.category)
                    }
                )
            }
        }
    }
}