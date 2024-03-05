package com.feature.company.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.featureapi.FeatureApi
import com.feature.company.ui.screen.CompanyViewModel
import javax.inject.Inject

interface CompanyApi : FeatureApi

class CompanyApiImpl @Inject constructor(private val companyViewModel: CompanyViewModel) :
    CompanyApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalCompanyFeatureApi(companyViewModel).registerGraph(navController, navGraphBuilder)
    }
}