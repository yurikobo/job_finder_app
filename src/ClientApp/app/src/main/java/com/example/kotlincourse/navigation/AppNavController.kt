package com.example.kotlincourse.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.core.common.navigation.NavigationConstants
import com.example.kotlincourse.ui.JobSearchingViewModel
import com.example.kotlincourse.ui.screens.HomeScreen

@Composable
fun AppNavController(
    jobSearchingViewModel: JobSearchingViewModel,
    navController: NavHostController,
    navigationProvider: NavigationProvider,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = NavigationConstants.HOME_SCREEN.nestedRoute,
        modifier = modifier
    ) {

        composable(NavigationConstants.HOME_SCREEN.nestedRoute) {
            HomeScreen(
                uiState = jobSearchingViewModel.screenUiState,
                retryAction = jobSearchingViewModel::getScreenInfo,
                onCompanyClick = { companyId ->
                    navController.navigate(NavigationConstants.COMPANY_DETAILS_SCREEN.nestedRoute + "/" + companyId)
                },
                onVacancyClick = { vacancyId ->
                    navController.navigate(NavigationConstants.VACANCY_DETAILS_SCREEN.nestedRoute + "/" + vacancyId)
                }
            )
        }
        navigationProvider.companyApi.registerGraph(navController, this)
        navigationProvider.vacancyApi.registerGraph(navController, this)
    }

}