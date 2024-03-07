package com.feature.resume.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.featureapi.FeatureApi
import com.feature.resume.ui.screen.ResumeViewModel
import javax.inject.Inject

interface ResumeApi : FeatureApi

class ResumeApiImpl @Inject constructor(private val resumeViewModel: ResumeViewModel) : ResumeApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalResumeFeatureApi(resumeViewModel).registerGraph(navController, navGraphBuilder)
    }

}

