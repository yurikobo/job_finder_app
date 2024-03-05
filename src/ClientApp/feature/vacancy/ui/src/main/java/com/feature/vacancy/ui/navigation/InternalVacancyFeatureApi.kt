package com.feature.vacancy.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation.NavigationConstants
import com.core.featureapi.FeatureApi
import com.feature.vacancy.ui.screen.VacancyScreen
import com.feature.vacancy.ui.screen.VacancyViewModel
import javax.inject.Inject

internal class InternalVacancyFeatureApi @Inject constructor(private val vacancyViewModel: VacancyViewModel) :
    FeatureApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            startDestination = NavigationConstants.VACANCY_DETAILS_SCREEN.screenRoute,
            route = NavigationConstants.VACANCY_DETAILS_SCREEN.nestedRoute + "/{vacancyId}"
        ) {
            composable(NavigationConstants.VACANCY_DETAILS_SCREEN.screenRoute) { backStackEntry ->
                val vacancyId = backStackEntry.arguments?.getString("vacancyId")?.toLongOrNull()
                LaunchedEffect(
                    key1 = true,
                    block = {
                        vacancyViewModel.vacancyId = vacancyId ?: 0L
                        vacancyViewModel.getScreenInfo()
                    })
                VacancyScreen(
                    vacancyViewModel.screenUiState,
                    vacancyViewModel::getScreenInfo,
                )
            }
        }
    }
}