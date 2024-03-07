package com.feature.infoscreen.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.featureapi.FeatureApi
import com.feature.infoscreen.ui.screen.InfoScreenViewModel
import javax.inject.Inject

interface InfoScreenApi : FeatureApi

class InfoScreenApiImpl @Inject constructor(private val infoScreenViewModel: InfoScreenViewModel) :
    InfoScreenApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalInfoScreenFeatureApi(infoScreenViewModel).registerGraph(navController, navGraphBuilder)
    }
}