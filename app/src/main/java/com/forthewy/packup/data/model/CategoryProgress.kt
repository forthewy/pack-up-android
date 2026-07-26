package com.forthewy.packup.data.model

data class CategoryProgress(
    val category: Category,
    val totalCount: Int,
    val isCheckedCount: Int
)