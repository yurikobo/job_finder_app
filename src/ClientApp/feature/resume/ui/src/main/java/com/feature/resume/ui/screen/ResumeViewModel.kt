package com.feature.resume.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiState
import com.core.common.models.ResumeWithTags
import com.feature.resume.domain.usecases.GetResumeUseCase
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class ResumeViewModel @Inject constructor(
    private val getResumeUseCase: GetResumeUseCase
) : ViewModel() {
    var screenUiState: UiState<ResumeWithTags?> by mutableStateOf(UiState.Loading())
        private set

    var resumeId: Long = 1L

    fun getScreenInfo() {
        viewModelScope.launch {
            screenUiState = UiState.Loading()
            screenUiState = try {
                UiState.Success(getResumeUseCase.execute(resumeId))
            } catch (e: IOException) {
                UiState.Error(e.message.toString())
            }
        }
    }
}