package com.example.kotlincourse.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiState
import com.example.kotlincourse.models.HomeScreenData
import com.feature.company.domain.usecases.GetCompaniesInfoListUseCase
import com.feature.vacancy.domain.usecases.GetVacancyInfoListUseCase
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


class JobSearchingViewModel @Inject constructor(
    private val getCompaniesInfoListUseCase: GetCompaniesInfoListUseCase,
    private val getVacancyInfoListUseCase: GetVacancyInfoListUseCase,
) : ViewModel() {

    var screenUiState: UiState<HomeScreenData> by mutableStateOf(UiState.Loading())
        private set

    init {
        getScreenInfo()
    }

    fun getScreenInfo() {
        viewModelScope.launch {
            screenUiState = UiState.Loading()
            screenUiState = try {
                UiState.Success(
                    HomeScreenData(
                        getCompaniesInfoListUseCase.execute(),
                        getVacancyInfoListUseCase.execute()
                    )
                )
            } catch (e: IOException) {
                UiState.Error(e.message.toString())
            }
        }
    }

}