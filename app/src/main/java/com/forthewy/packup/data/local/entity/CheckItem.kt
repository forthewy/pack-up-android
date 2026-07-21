package com.forthewy.packup.data.local.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.forthewy.packup.data.model.Category

@Entity
data class CheckItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val category: Category,

    val parentId: Int? = null,

    val title: String,

    val note: String? = null,

    val isChecked: Boolean = false,

    val createdAt: Long = System.currentTimeMillis()
)