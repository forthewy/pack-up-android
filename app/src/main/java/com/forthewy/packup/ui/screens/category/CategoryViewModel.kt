package com.forthewy.packup.ui.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forthewy.packup.data.repository.CheckItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private  val repository: CheckItemRepository
) : ViewModel() {

    val uiState: StateFlow<CategoryUiState> =
        repository.getCategoryProgress()
            .map { list ->
                CategoryUiState(
                    categories = list.map {

                        val progress =
                            if (it.totalCount == 0) 0
                            else ((it.isCheckedCount.toFloat() / it.totalCount) * 100).roundToInt()

                        CategoryItemUiState(
                            category = it.category,
                            itemCount = it.totalCount,
                            checkedCount = it.isCheckedCount,
                            progress = progress
                        )
                    }
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CategoryUiState()
            )
}
