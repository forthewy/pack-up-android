package com.forthewy.packup.data.local.dao

import com.forthewy.packup.data.model.Category
import com.forthewy.packup.data.local.entity.CheckItem
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update

@Dao
interface CheckItemDao {

    @Query("SELECT * FROM CheckItem")
    suspend fun getAll(): List<CheckItem>

    @Insert
    suspend fun insert(item: CheckItem)

    @Delete
    suspend fun delete(item: CheckItem)

    @Update
    suspend fun update(item: CheckItem)

    @Query("SELECT * FROM CheckItem WHERE category = :category")
    suspend fun getItemsByCategory(category: Category): List<CheckItem>
}