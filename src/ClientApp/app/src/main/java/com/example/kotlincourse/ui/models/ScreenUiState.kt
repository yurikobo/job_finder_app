package com.example.kotlincourse.ui.models

import com.example.domain.models.VacancyDomainInfo
import com.feature.company.domain.models.CompanyDomain
import com.feature.company.domain.models.CompanyDomainInfo

sealed interface ScreenUiState {

    sealed interface Success : ScreenUiState {
        data class HomeScreenData(
            val companyInfoList: List<CompanyDomainInfo>,
            val vacancyInfoList: List<VacancyDomainInfo>
        ) : Success

        data class CompanyDetailsScreenData(
            val company: CompanyDomain
        ) : Success

        data class VacancyDetailsScreenData(
            val vacancy: com.example.domain.models.VacancyDomain
        ) : Success

        data class ResumeScreenData(
            val tmp: String
//            val resume: Resume
        )
    }

    object Error : ScreenUiState

    object Loading : ScreenUiState
}