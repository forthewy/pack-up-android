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
    // ---- GET -------
    // 카테고리별 아이템
    fun getItemsByCategory(category: Category): Flow<List<CheckItem>> {
        return dao.getItemsByCategory(category)
    }

    // 자식 아이템
    suspend fun getChildren(parentId: Int): List<CheckItem> {
        return dao.getChildren(parentId)
    }

    suspend fun getItemById(id: Int): CheckItem? {
        return dao.getItemById(id)
    }

    // 추가
    suspend fun insert(item: CheckItem) {
        dao.insert(item)
    }

    // 체크된 아이템 추가
    suspend fun insertCheckedItems(items: List<CheckItem>) {
        dao.insertCheckedItems(items)
    }

    suspend fun update(item: CheckItem) {
        dao.update(item)
    }

    // ------- DELETE -----------
    suspend fun delete(item: CheckItem) {
        dao.delete(item)
    }

    suspend fun deleteChildrenByParentId(parentId: Int) {
        dao.deleteChildrenByParentId(parentId)
    }

    // 해당 카테고리 전체 삭제
    suspend fun deleteAllByCategory(category: Category) {
        dao.deleteAllByCategory(category)
    }
    
    
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