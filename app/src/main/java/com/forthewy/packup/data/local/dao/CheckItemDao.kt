package com.forthewy.packup.data.local.dao

import com.forthewy.packup.data.model.Category
import com.forthewy.packup.data.local.entity.CheckItem
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update
import com.forthewy.packup.data.model.CategoryProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckItemDao {

    @Query("SELECT * FROM CheckItem")
    fun getAll(): Flow<List<CheckItem>>

    @Insert
    suspend fun insert(item: CheckItem)

    // 삭제
    @Delete
    suspend fun delete(item: CheckItem)

    // 해당 카테고리 전체 삭제
    @Query("DELETE FROM CheckItem WHERE category = :category")
    suspend fun deleteAllByCategory(category: Category)

    // 수정
    @Update
    suspend fun update(item: CheckItem)

    // 카테고리별 아이템
    @Query("""
    SELECT *
    FROM CheckItem
    WHERE category = :category
    """)
    fun getItemsByCategory(
        category: Category
    ): Flow<List<CheckItem>>

    @Query("""
    SELECT COUNT(*)
    FROM CheckItem
    WHERE category = :category
    """)
    fun getItemCount(
        category: Category
    ): Flow<Int>


    @Query("""
    SELECT
        category,
        COUNT(*) AS totalCount,
        SUM(CASE WHEN isChecked THEN 1 ELSE 0 END) AS isCheckedCount
    FROM CheckItem
    GROUP BY category
    """)
    fun getCategoryProgress(): Flow<List<CategoryProgress>>
}