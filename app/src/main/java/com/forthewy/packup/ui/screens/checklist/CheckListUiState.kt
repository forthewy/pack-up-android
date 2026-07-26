package com.forthewy.packup.ui.screens.checklist

import com.forthewy.packup.data.local.entity.CheckItem

data class CheckListUiState(
    val items: List<CheckItem> = emptyList(),
    val progress: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null


)