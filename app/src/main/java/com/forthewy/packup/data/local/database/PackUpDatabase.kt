package com.forthewy.packup.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.forthewy.packup.data.local.converter.CategoryConverter
import com.forthewy.packup.data.local.dao.CheckItemDao
import com.forthewy.packup.data.local.entity.CheckItem


@Database(
    entities = [CheckItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CategoryConverter::class)
abstract class PackUpDatabase : RoomDatabase() {

    abstract fun checkItemDao(): CheckItemDao
}