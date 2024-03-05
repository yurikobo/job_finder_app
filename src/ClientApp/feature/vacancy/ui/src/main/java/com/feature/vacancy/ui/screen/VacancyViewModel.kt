package com.feature.vacancy.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiState
import com.core.common.models.Vacancy
import com.feature.vacancy.domain.usecases.GetVacancyDetailsUseCase
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class VacancyViewModel @Inject constructor(
    private val getVacancyDetailsUseCase: GetVacancyDetailsUseCase
) :
    ViewModel() {

    var screenUiState: UiState<Vacancy?> by mutableStateOf(UiState.Loading())
        private set

    var vacancyId: Long = 1L

    fun getScreenInfo() {
        viewModelScope.launch {
            screenUiState = UiState.Loading()
            screenUiState = try {
                UiState.Success(getVacancyDetailsUseCase.execute(vacancyId))
            } catch (e: IOException) {
                UiState.Error(e.message.toString())
            }
        }
    }
}