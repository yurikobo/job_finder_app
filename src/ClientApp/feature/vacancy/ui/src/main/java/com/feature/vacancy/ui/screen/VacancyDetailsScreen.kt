package com.feature.vacancy.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.common.UiState
import com.core.common.models.Vacancy
import com.feature.vacancy.ui.R


@Composable
fun VacancyScreen(
    uiState: UiState<Vacancy?>,
    onRetry: () -> Unit,
    onCompanyRefClick: () -> Unit
) {

    when (uiState) {
        is UiState.Success -> VacancyDetails(uiState.data, onCompanyRefClick)
        is UiState.Loading -> LoadingScreen()
        is UiState.Error -> ErrorScreen(uiState.message, retryAction = { onRetry.invoke() })
    }
}

@Composable
fun ErrorScreen(message: String?, retryAction: () -> Unit) {
    Column {
        message?.let { Text(it) }
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen() {
    Text(stringResource(R.string.now_loading))
}

@Composable
fun VacancyDetails(data: Vacancy?, onCompanyRefClick: () -> Unit) {
    if (data == null) {
        Text("Nothing to show")
    } else {
        val modifier = Modifier.padding(10.dp)
        Column {
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.job_title, data.profession)
            )
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.candidate_level, data.level)
            )
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.salary_level, data.salary)
            )
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.vacancy_description, data.description)
            )
            Button(
                modifier = modifier,
                onClick = onCompanyRefClick
            ) {
                Text("To the company")
            }
        }
    }
}
