package com.forthewy.packup.ui.screens.category

import com.forthewy.packup.data.model.Category

data class CategoryUiState(
    val categories: List<CategoryItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class CategoryItemUiState(
    val category: Category,
    val itemCount: Int,
    val checkedCount: Int,
    val progress: Int
)
