package com.feature.company.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiState
import com.core.common.models.Company
import com.feature.company.domain.usecases.GetCompanyDetailsUseCase
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class CompanyViewModel @Inject constructor(
    private val getCompaniesDetailsUseCase: GetCompanyDetailsUseCase
) :
    ViewModel() {

    var screenUiState: UiState<Company?> by mutableStateOf(UiState.Loading())
        private set

    var companyId: Long = 1L

    fun getScreenInfo() {
        viewModelScope.launch {
            screenUiState = UiState.Loading()
            screenUiState = try {
                UiState.Success(getCompaniesDetailsUseCase.execute(companyId))
            } catch (e: IOException) {
                UiState.Error(e.message.toString())
            }
        }
    }
}