package com.forthewy.packup.ui.screens.checklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.forthewy.packup.data.local.entity.CheckItem
import com.forthewy.packup.data.model.Category
import com.forthewy.packup.data.repository.CheckItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckListViewModel @Inject constructor(
    private val repository: CheckItemRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CheckListUiState())
    val uiState: StateFlow<CheckListUiState> = _uiState
    fun load(category: Category) {
        repository.getItemsByCategory(category)
            .map { items ->
                val progress =
                    if (items.isEmpty()) 0
                    else (items.count { it.isChecked } * 100) / items.size

                CheckListUiState(
                    items = items,
                    progress = progress
                )
            }
            .onEach { _uiState.value = it }
            .launchIn(viewModelScope)
    }

//    fun getItems(category: Category) =
//        repository.getItemsByCategory(category)

    fun addItem(
        category: Category,
        title: String
    ) {
        viewModelScope.launch {
            repository.insert(
                CheckItem(
                    category = category,
                    title = title
                )
            )
        }
    }

    fun updateItem(item: CheckItem) {
        viewModelScope.launch {
            repository.update(item)
        }
    }

    // 삭제
    fun deleteItem(item: CheckItem) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    // 해당 카테고리 전체 삭제
    fun deleteAll(category: Category) {

    }
}