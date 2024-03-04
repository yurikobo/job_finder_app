package com.example.kotlincourse.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kotlincourse.R
import com.example.kotlincourse.ui.components.ErrorScreen
import com.example.kotlincourse.ui.components.LoadingScreen
import com.example.kotlincourse.ui.models.ScreenUiState


@Composable
fun VacancyScreen(
    uiState: ScreenUiState,
    retryAction: () -> Unit,
    onCompanyClick: () -> Unit
) {
    when (uiState) {
        is ScreenUiState.Success.VacancyDetailsScreenData -> VacancyDetails(uiState, onCompanyClick)
        is ScreenUiState.Loading -> LoadingScreen()
        else -> ErrorScreen(retryAction = { retryAction.invoke() })
    }


}


@Composable
fun VacancyDetails(
    uiState: ScreenUiState.Success.VacancyDetailsScreenData,
    onCompanyClick: () -> Unit
) {

    val modifier = Modifier
        .padding(10.dp)
    Column {
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.job_title, uiState.vacancy.profession)
        )
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.candidate_level, uiState.vacancy.level)
        )
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.salary_level, uiState.vacancy.salary)
        )
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.vacancy_description, uiState.vacancy.description)
        )
        Button(onClick = { onCompanyClick.invoke() }) {
            Text("To the company")
        }
    }

}


