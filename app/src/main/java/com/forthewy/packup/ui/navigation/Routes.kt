package com.forthewy.packup.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
object  SettingRoute

@Serializable
object LicenseRoute

@Serializable
object CategoryRoute

@Serializable
data class CheckListRoute(val categoryId: Int)