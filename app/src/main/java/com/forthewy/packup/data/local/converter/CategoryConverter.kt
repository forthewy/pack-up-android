package com.forthewy.packup.data.local.converter

import androidx.room.TypeConverter
import com.forthewy.packup.data.model.Category

class CategoryConverter {

    @TypeConverter
    fun fromCategory(category: Category): String {
        return category.name
    }

    @TypeConverter
    fun toCategory(value: String): Category {
        return Category.valueOf(value)
    }
}