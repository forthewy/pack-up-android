package com.forthewy.packup.ui.screens.checklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forthewy.packup.data.local.entity.CheckItem
import com.forthewy.packup.data.model.Category
import com.forthewy.packup.data.repository.CheckItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
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
        title: String,
        parentId: Int? = null
    ) {
        viewModelScope.launch {
            repository.insert(
                CheckItem(
                    category = category,
                    title = title,
                    parentId = parentId
                )
            )
        }
    }

    // 수정
    fun updateItem(item: CheckItem) {
        viewModelScope.launch {
            repository.update(item)
        }
    }

    // 부모체크시 자식도 체크
    fun toggleParent(
        parent: CheckItem,
        checked: Boolean
    ) {
        viewModelScope.launch {

            repository.update(
                parent.copy(isChecked = checked)
            )

            val children = repository.getChildren(parent.id)

            children.forEach { child ->
                repository.update(
                    child.copy(isChecked = checked)
                )
            }
        }
    }

    fun toggleChild(child: CheckItem, checked: Boolean) {
        viewModelScope.launch {
            repository.update(
                child.copy(isChecked = checked)
            )

            child.parentId?.let { parentId ->
                updateParentCheckState(
                    parentId,
                    child.id,
                    checked
                )
            }
        }
    }

    private suspend fun updateParentCheckState(
        parentId: Int,
        changedChildId: Int,
        checked: Boolean
    ) {
        val siblings = _uiState.value.items
            .filter { it.parentId == parentId }

        val allChecked = siblings.all {
            if (it.id == changedChildId) {
                checked
            } else {
                it.isChecked
            }
        }
        val parent = repository.getItemById(parentId) ?: return

        repository.update(
            parent.copy(isChecked = allChecked)
        )


    }

    // ---------- DELETE ----------------

    // 삭제
    fun deleteItem(item: CheckItem) {
        viewModelScope.launch {
            if (item.parentId == null) {
                repository.deleteChildrenByParentId(item.id)
            }

            repository.delete(item)
        }
    }

    // 해당 카테고리 전체 삭제
    fun deleteAll(category: Category) {
        viewModelScope.launch {
            repository.deleteAllByCategory(category)
        }
    }

    // 추천 리스트에서 체크된 아이템 추가
    fun addCheckedSuggestedItems(
        category: Category,
        titles: List<String>,
    ) {
        viewModelScope.launch {

            val items = titles.map {
                CheckItem(
                    category = category,
                    title = it
                    // parentId는 모두 null
                )
            }

            repository.insertCheckedItems(items)
        }
    }
}