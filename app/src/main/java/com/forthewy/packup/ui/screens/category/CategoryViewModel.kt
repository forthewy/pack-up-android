package com.forthewy.packup.ui.screens.category

import androidx.lifecycle.ViewModel
import com.forthewy.packup.data.model.Category
import com.forthewy.packup.data.repository.CheckItemRepository

class CategoryViewModel(
    private  val repository: CheckItemRepository
) : ViewModel() {

    val categories = Category.entries
//    fun getCategoryProgress(category: Category)
//
//    fun getCategoryCount(category: Category)
}