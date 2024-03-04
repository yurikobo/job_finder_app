package com.example.kotlincourse.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetVacancyDetailsUseCase
import com.example.domain.usecases.GetVacancyInfoListUseCase
import com.example.kotlincourse.ui.JobAppScreen.COMPANY_DETAILS
import com.example.kotlincourse.ui.JobAppScreen.START
import com.example.kotlincourse.ui.JobAppScreen.VACANCY_DETAILS
import com.example.kotlincourse.ui.models.ScreenUiState
import com.feature.company.domain.usecases.GetCompaniesInfoListUseCase
import com.feature.company.domain.usecases.GetCompanyDetailsUseCase
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


class JobSearchingViewModel @Inject constructor(
    private val getCompaniesInfoListUseCase: GetCompaniesInfoListUseCase,
    private val getVacancyInfoListUseCase: GetVacancyInfoListUseCase,
    private val getCompanyDetailsUseCase: GetCompanyDetailsUseCase,
    private val getVacancyDetailsUseCase: GetVacancyDetailsUseCase
) : ViewModel() {

    var screenUiState: ScreenUiState by mutableStateOf(ScreenUiState.Loading)
        private set

    var currentScreen: JobAppScreen = START

    var companyId: Long = 0

    var vacancyId: Long = 0

    init {
        getScreenInfo()
    }

    fun getScreenInfo() {
        viewModelScope.launch {
            screenUiState = ScreenUiState.Loading
            screenUiState = try {
                when (currentScreen) {
                    START -> ScreenUiState.Success.HomeScreenData(
                        getCompaniesInfoListUseCase.execute(),
                        getVacancyInfoListUseCase.execute()
                    )

                    COMPANY_DETAILS -> ScreenUiState.Success.CompanyDetailsScreenData(
                        getCompanyDetailsUseCase.execute(companyId) ?: throw IOException(),
                    )

                    VACANCY_DETAILS -> ScreenUiState.Success.VacancyDetailsScreenData(
                        getVacancyDetailsUseCase.execute(vacancyId) ?: throw IOException(),
                    )

//                    RESUME_SCREEN -> ScreenUiState.Success.ResumeScreenData(
//                        "test"
//                    )
                    else -> throw IOException()
                }

            } catch (e: IOException) {
                ScreenUiState.Error
            }
        }
    }

}