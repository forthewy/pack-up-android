package com.forthewy.packup.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.forthewy.packup.ui.screens.home.HomeScreen
import com.forthewy.packup.ui.screens.category.CategoryScreen
import com.forthewy.packup.ui.screens.checklist.CheckListScreen
import com.forthewy.packup.ui.screens.setting.SettingScreen
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer

@Composable
fun PackUpNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {

        composable<HomeRoute> {
            HomeScreen(
                onStartClick = {
                    navController.navigate(CategoryRoute)
                },
                onSettingClick = {
                    navController.navigate(SettingRoute)
                },
                onLicenseClick = {
                    navController.navigate(LicenseRoute)
                }
            )
        }

        composable<SettingRoute>{
            SettingScreen()
        }

        composable<LicenseRoute> {
            LibrariesContainer()
        }

        composable<CategoryRoute> {
            CategoryScreen(
                onCategoryClick = { category ->
                    navController.navigate(
                        CheckListRoute(category)
                    )
                }
            )
        }

        composable<CheckListRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<CheckListRoute>()

            CheckListScreen(
                category = route.category
            )
        }
    }
}