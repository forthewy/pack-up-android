package com.forthewy.packup.data.repository

import com.forthewy.packup.data.local.dao.CheckItemDao
import com.forthewy.packup.data.local.entity.CheckItem
import com.forthewy.packup.data.model.Category
import javax.inject.Inject

class CheckItemRepository @Inject constructor(
    private val dao: CheckItemDao
) {

    suspend fun getAllItems(): List<CheckItem> {
        return dao.getAll()
    }

    suspend fun getItemsByCategory(category: Category): List<CheckItem> {
        return dao.getItemsByCategory(category)
    }

    suspend fun insert(item: CheckItem) {
        dao.insert(item)
    }

    suspend fun update(item: CheckItem) {
        dao.update(item)
    }

    suspend fun delete(item: CheckItem) {
        dao.delete(item)
    }
}