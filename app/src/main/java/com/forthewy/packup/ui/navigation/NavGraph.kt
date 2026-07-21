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
                }
            )
        }

        composable<CategoryRoute> {
            CategoryScreen(
                onCategoryClick = { categoryId ->
                    navController.navigate(
                        CheckListRoute(categoryId)
                    )
                }
            )
        }

        composable<CheckListRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<CheckListRoute>()

            CheckListScreen(
                categoryId = route.categoryId
            )
        }
    }
}