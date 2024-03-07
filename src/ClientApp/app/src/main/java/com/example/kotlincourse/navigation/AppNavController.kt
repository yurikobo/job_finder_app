package com.example.kotlincourse.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.common.navigation.NavigationConstants


@Composable
fun AppNavController(
    navController: NavHostController,
    navigationProvider: NavigationProvider,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = NavigationConstants.HOME_SCREEN.nestedRoute,
        modifier = modifier
    ) {

        navigationProvider.infoScreenApi.registerGraph(navController, this)
        navigationProvider.companyApi.registerGraph(navController, this)
        navigationProvider.vacancyApi.registerGraph(navController, this)
        navigationProvider.resumeApi.registerGraph(navController, this)
    }

}