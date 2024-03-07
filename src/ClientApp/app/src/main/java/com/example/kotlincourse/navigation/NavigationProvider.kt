package com.example.kotlincourse.navigation

import com.feature.company.ui.navigation.CompanyApi
import com.feature.infoscreen.ui.navigation.InfoScreenApi
import com.feature.resume.ui.navigation.ResumeApi
import com.feature.vacancy.ui.navigation.VacancyApi
import javax.inject.Inject

data class NavigationProvider @Inject constructor(
    val infoScreenApi: InfoScreenApi,
    val companyApi: CompanyApi,
    val vacancyApi: VacancyApi,
    val resumeApi: ResumeApi
)
