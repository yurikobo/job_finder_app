package com.example.kotlincourse.models

import com.core.common.models.CompanyInfo
import com.core.common.models.VacancyInfo

data class HomeScreenData(
    val companiesList: List<CompanyInfo>,
    val vacanciesList: List<VacancyInfo>
)
