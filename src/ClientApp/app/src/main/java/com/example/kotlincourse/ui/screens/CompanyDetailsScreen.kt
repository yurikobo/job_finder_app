package com.example.kotlincourse.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kotlincourse.R
import com.example.kotlincourse.ui.components.ErrorScreen
import com.example.kotlincourse.ui.components.LoadingScreen
import com.example.kotlincourse.ui.models.ScreenUiState
import com.feature.company.domain.models.CompanyDomain


@Composable
fun CompanyScreen(
    uiState: ScreenUiState,
    retryAction: () -> Unit,
    onVacancyClick: (Long) -> Unit,
) {
    when (uiState) {
        is ScreenUiState.Success.CompanyDetailsScreenData -> CompanyDetailsScreen(
            uiState,
            onVacancyClick
        )

        is ScreenUiState.Loading -> LoadingScreen()
        else -> ErrorScreen(retryAction = { retryAction.invoke() })
    }


}


@Composable
fun CompanyDetailsScreen(
    uiState: ScreenUiState.Success.CompanyDetailsScreenData,
    onVacancyClick: (Long) -> Unit
) {
    val company = uiState.company
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CompanyDetails(company)
//        LazyColumn {
//            items(company.l) { vacancy ->
//                VacancyCard(
//                    vacancyInfo = com.example.domain.models.VacancyDomainInfo(
//                        jobTitle = vacancy.profession,
//                        candidateLevel = vacancy.level,
//                        salaryLevel = vacancy.salary,
//                        companyName = company.name
//                    ),
//                    onVacancyClick = { onVacancyClick(vacancy.id) }
//                )
//            }
//        }
    }
}


@Composable
fun CompanyDetails(company: CompanyDomain) {
    val modifier = Modifier
        .padding(10.dp)
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.company_name, company.name)
    )
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.field_of_activity, company.fieldOfActivity.toString())
    )
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.phone_number, company.contact)
    )
//    if (company.listOfVacancies.isNotEmpty()) {
//        Text(
//            modifier = modifier,
//            text = "List of vacancies:"
//        )
//    }
}
