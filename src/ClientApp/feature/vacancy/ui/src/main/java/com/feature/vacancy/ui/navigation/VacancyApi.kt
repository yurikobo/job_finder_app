package com.feature.vacancy.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.featureapi.FeatureApi
import com.feature.vacancy.ui.screen.VacancyViewModel
import javax.inject.Inject

interface VacancyApi : FeatureApi

class VacancyApiImpl @Inject constructor(private val vacancyViewModel: VacancyViewModel) :
    VacancyApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalVacancyFeatureApi(vacancyViewModel).registerGraph(navController, navGraphBuilder)
    }
}