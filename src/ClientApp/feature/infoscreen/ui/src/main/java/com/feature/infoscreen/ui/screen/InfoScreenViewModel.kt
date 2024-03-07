package com.feature.infoscreen.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiState
import com.feature.infoscreen.domain.usecases.GetCompaniesInfoListUseCase
import com.feature.infoscreen.domain.usecases.GetVacancyInfoListUseCase
import com.feature.infoscreen.ui.models.InfoScreenData
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


class InfoScreenViewModel @Inject constructor(
    private val getCompaniesInfoListUseCase: GetCompaniesInfoListUseCase,
    private val getVacancyInfoListUseCase: GetVacancyInfoListUseCase,
) : ViewModel() {

    var screenUiState: UiState<InfoScreenData> by mutableStateOf(UiState.Loading())
        private set

    init {
        getScreenInfo()
    }

    fun getScreenInfo() {
        viewModelScope.launch {
            screenUiState = UiState.Loading()
            screenUiState = try {
                UiState.Success(
                    InfoScreenData(
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