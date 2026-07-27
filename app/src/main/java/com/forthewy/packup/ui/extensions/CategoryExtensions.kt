package com.forthewy.packup.ui.extensions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Luggage
import androidx.compose.material.icons.filled.Room
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector
import com.forthewy.packup.R
import com.forthewy.packup.data.model.Category

fun Category.titleRes(): Int = when (this) {
    Category.TRAVEL -> R.string.category_travel
    Category.STUDY -> R.string.category_study
    Category.SHOPPING -> R.string.category_shopping
    Category.MOVE -> R.string.category_move
    Category.FITNESS -> R.string.category_fitness
    Category.DAILY -> R.string.category_daily
    Category.WORK -> R.string.category_work
    Category.ETC -> R.string.category_etc
}

fun Category.icon(): ImageVector = when (this) {
    Category.TRAVEL -> Icons.Default.Luggage
    Category.STUDY -> Icons.Default.School
    Category.SHOPPING -> Icons.Default.ShoppingBag
    Category.MOVE -> Icons.Default.Room
    Category.FITNESS -> Icons.Default.FitnessCenter
    Category.DAILY -> Icons.Default.Checkroom
    Category.WORK -> Icons.Default.Work
    Category.ETC -> Icons.Default.Description
}