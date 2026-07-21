package com.forthewy.packup.ui.screens.checklist

import androidx.lifecycle.ViewModel
import com.forthewy.packup.data.repository.CheckItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckListViewModel @Inject constructor(
    private val repository: CheckItemRepository
) : ViewModel() {
    fun getItem() {}
    fun addItem() {}
    fun updateItem() {}
    fun deleteItem() {}
}