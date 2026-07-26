package com.forthewy.packup.data.repository

import com.forthewy.packup.data.local.dao.CheckItemDao
import com.forthewy.packup.data.local.entity.CheckItem
import com.forthewy.packup.data.model.Category
import com.forthewy.packup.data.model.CategoryProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CheckItemRepository @Inject constructor(
    private val dao: CheckItemDao
) {


    fun getAllItems(): Flow<List<CheckItem>> {
        return dao.getAll()
    }

    // 카테고리별 아이템
    fun getItemsByCategory(category: Category): Flow<List<CheckItem>> {
        return dao.getItemsByCategory(category)
    }

    suspend fun insert(item: CheckItem) {
        dao.insert(item)
    }

    suspend fun update(item: CheckItem) {
        dao.update(item)
    }

    // 삭제
    suspend fun delete(item: CheckItem) {
        dao.delete(item)
    }

    // 해당 카테고리 전체 삭제
    suspend fun deleteAllByCategory(category: Category)
    
    
    fun getItemCount(
        category: Category
    ): Flow<Int> {
        return dao.getItemCount(category)
    }

    fun getCategoryProgress(): Flow<List<CategoryProgress>> {
        return dao.getCategoryProgress().map { roomResult ->

            val progressMap = roomResult.associateBy { it.category }

            Category.entries.map { category ->
                progressMap[category]
                    ?: CategoryProgress(
                        category = category,
                        totalCount = 0,
                        isCheckedCount = 0
                    )
            }
        }
    }
}