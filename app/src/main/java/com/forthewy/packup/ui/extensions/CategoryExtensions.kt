package com.forthewy.packup.ui.extensions

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