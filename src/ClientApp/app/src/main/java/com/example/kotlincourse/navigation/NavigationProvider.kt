package com.example.kotlincourse.navigation

import com.feature.company.ui.navigation.CompanyApi
import com.feature.vacancy.ui.navigation.VacancyApi
import javax.inject.Inject

data class NavigationProvider @Inject constructor(
    val companyApi: CompanyApi,
    val vacancyApi: VacancyApi
)
