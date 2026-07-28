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

    // ------------- GET------------
    // 카테고리별 아이템
    @Query(
        """
    SELECT *
    FROM CheckItem
    WHERE category = :category
    """
    )
    fun getItemsByCategory(
        category: Category
    ): Flow<List<CheckItem>>

    // 카테고리별 갯수
    @Query(
        """
    SELECT COUNT(*)
    FROM CheckItem
    WHERE category = :category
    """
    )
    fun getItemCount(
        category: Category
    ): Flow<Int>


    // 카테고리별 완료갯수
    @Query(
        """
    SELECT
        category,
        COUNT(*) AS totalCount,
        SUM(CASE WHEN isChecked THEN 1 ELSE 0 END) AS isCheckedCount
    FROM CheckItem
    GROUP BY category
    """
    )
    fun getCategoryProgress(): Flow<List<CategoryProgress>>

    @Query("SELECT * FROM CheckItem WHERE parentId = :parentId")
    suspend fun getChildren(parentId: Int): List<CheckItem>

    @Query("SELECT * FROM CheckItem WHERE id = :id")
    suspend fun getItemById(id: Int): CheckItem?

    // ------------- INSERT ------------
    @Insert
    suspend fun insert(item: CheckItem)

    @Insert
    suspend fun insertCheckedItems(items: List<CheckItem>)

    // --------------- DELETE ------------
    // 삭제
    @Delete
    suspend fun delete(item: CheckItem)

    @Query("DELETE FROM CheckItem WHERE parentId = :parentId")
    suspend fun deleteChildrenByParentId(parentId: Int)

    // 해당 카테고리 전체 삭제
    @Query("DELETE FROM CheckItem WHERE category = :category")
    suspend fun deleteAllByCategory(category: Category)

    // ----------- UPDATE ------------
    // 수정
    @Update
    suspend fun update(item: CheckItem)

}