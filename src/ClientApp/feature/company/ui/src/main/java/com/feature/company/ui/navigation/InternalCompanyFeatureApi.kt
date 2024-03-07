package com.feature.company.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation.NavigationConstants
import com.core.featureapi.FeatureApi
import com.feature.company.ui.screen.CompanyScreen
import com.feature.company.ui.screen.CompanyViewModel
import javax.inject.Inject

internal class InternalCompanyFeatureApi @Inject constructor(private val companyViewModel: CompanyViewModel) :
    FeatureApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            startDestination = NavigationConstants.COMPANY_DETAILS_SCREEN.screenRoute,
            route = NavigationConstants.COMPANY_DETAILS_SCREEN.nestedRoute + "/{companyId}"
        ) {
            composable(NavigationConstants.COMPANY_DETAILS_SCREEN.screenRoute) { backStackEntry ->
                val companyId = backStackEntry.arguments?.getString("companyId")?.toLongOrNull()
                LaunchedEffect(
                    key1 = true,
                    block = {
                        companyViewModel.companyId = companyId ?: 0L
                        companyViewModel.getScreenInfo()
                    })
                CompanyScreen(
                    uiState = companyViewModel.screenUiState,
                    onRetry = companyViewModel::getScreenInfo,
                    onVacancyClick = { vacancyID, companyID ->
                        navController.navigate(NavigationConstants.VACANCY_DETAILS_SCREEN.nestedRoute + "/$vacancyID=$companyID")
                    }
                )
            }
        }
    }
}