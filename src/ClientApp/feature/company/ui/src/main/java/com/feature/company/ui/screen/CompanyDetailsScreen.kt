package com.feature.company.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.common.UiState
import com.core.common.models.Company
import com.core.common.models.VacancyInfo
import com.feature.company.ui.R


@Composable
fun CompanyScreen(
    uiState: UiState<Company?>,
    onRetry: () -> Unit,
    onVacancyClick: (vacancyId: Long, Long) -> Unit
) {
    when (uiState) {
        is UiState.Success -> CompanyDetails(uiState.data, onVacancyClick)
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
fun CompanyDetails(company: Company?, onVacancyClick: (Long, Long) -> Unit) {
    if (company == null) {
        Text(stringResource(R.string.nothing_to_show))
    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val modifier = Modifier
                .padding(10.dp)
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.company_name, company.name)
            )
            Text(
                modifier = modifier,
                text = stringResource(
                    id = R.string.field_of_activity,
                    company.fieldOfActivity.toString()
                )
            )
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.phone_number, company.contact)
            )
            if (company.listOfVacancies.isNotEmpty()) {
                Text(
                    modifier = modifier,
                    text = stringResource(R.string.list_of_vacancies)
                )
                LazyColumn {
                    items(company.listOfVacancies) { vacancy ->
                        VacancyCard(
                            vacancyInfo = VacancyInfo(
                                jobTitle = vacancy.profession,
                                candidateLevel = vacancy.level,
                                salaryLevel = vacancy.salary,
                                companyName = company.name
                            )
                        ) { onVacancyClick(vacancy.id, company.id) }
                    }
                }
            } else {
                Text(
                    modifier = modifier,
                    text = stringResource(R.string.no_vacancies_yet)
                )
            }

        }
    }
}

@Composable
fun VacancyCard(
    vacancyInfo: VacancyInfo,
    onVacancyClick: () -> Unit
) {
    val modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
    Card(
        modifier = modifier.clickable { onVacancyClick.invoke() }
    ) {
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.job_title, vacancyInfo.jobTitle)
        )
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.candidate_level, vacancyInfo.candidateLevel)
        )
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.salary_level, vacancyInfo.salaryLevel)
        )
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.company_name, vacancyInfo.companyName)
        )
    }

}

