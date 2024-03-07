package com.feature.infoscreen.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation.NavigationConstants
import com.core.featureapi.FeatureApi
import com.feature.infoscreen.ui.screen.HomeScreen
import com.feature.infoscreen.ui.screen.InfoScreenViewModel
import javax.inject.Inject

internal class InternalInfoScreenFeatureApi @Inject constructor(private val infoScreenViewModel: InfoScreenViewModel) :
    FeatureApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            startDestination = NavigationConstants.HOME_SCREEN.screenRoute,
            route = NavigationConstants.HOME_SCREEN.nestedRoute
        ) {
            composable(NavigationConstants.HOME_SCREEN.screenRoute) {
                HomeScreen(
                    uiState = infoScreenViewModel.screenUiState,
                    retryAction = infoScreenViewModel::getScreenInfo,
                    onCompanyClick = { companyId ->
                        navController.navigate(NavigationConstants.COMPANY_DETAILS_SCREEN.nestedRoute + "/$companyId")
                    },
                    onVacancyClick = { vacancyId, companyId ->
                        navController.navigate(NavigationConstants.VACANCY_DETAILS_SCREEN.nestedRoute + "/$vacancyId=$companyId")
                    }
                )
            }
        }
    }
}