package com.feature.infoscreen.ui.models

import com.core.common.models.CompanyInfo
import com.core.common.models.VacancyInfo

data class InfoScreenData(
    val companiesList: List<CompanyInfo>,
    val vacanciesList: List<VacancyInfo>
)
