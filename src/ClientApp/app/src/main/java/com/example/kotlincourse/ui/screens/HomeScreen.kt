package com.example.kotlincourse.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kotlincourse.R
import com.example.kotlincourse.ui.components.ErrorScreen
import com.example.kotlincourse.ui.components.LoadingScreen
import com.example.kotlincourse.ui.models.ScreenUiState
import com.example.kotlincourse.ui.models.TabContentType
import com.example.kotlincourse.ui.models.TabContentType.COMPANIES
import com.example.kotlincourse.ui.models.TabContentType.VACANCIES
import com.example.kotlincourse.ui.models.TabItem
import com.feature.company.domain.models.CompanyDomainInfo


@Composable
fun HomeScreen(
    uiState: ScreenUiState,
    retryAction: () -> Unit,
    onCompanyClick: (Long) -> Unit,
    onVacancyClick: (Long) -> Unit
) {
    val tabItems = listOf(
        TabItem(
            contentType = COMPANIES,
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home
        ),
        TabItem(
            contentType = VACANCIES,
            unselectedIcon = Icons.Outlined.Person,
            selectedIcon = Icons.Filled.Person
        )
    )
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, tabItem ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(text = tabItem.contentType.toString())
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedTabIndex) {
                                tabItem.selectedIcon
                            } else {
                                tabItem.unselectedIcon
                            },
                            contentDescription = tabItem.contentType.toString()
                        )
                    }
                )
            }

        }

        when (uiState) {
            is ScreenUiState.Success.HomeScreenData -> DetailsScreen(
                uiState,
                tabItems[selectedTabIndex].contentType,
                onCompanyClick,
                onVacancyClick
            )

            is ScreenUiState.Loading -> LoadingScreen()
            else -> ErrorScreen(retryAction)
        }

    }
}


@Composable
fun DetailsScreen(
    uiState: ScreenUiState.Success.HomeScreenData,
    tabType: TabContentType,
    onCompanyClick: (Long) -> Unit,
    onVacancyClick: (Long) -> Unit
) {
    LazyColumn {
        when (tabType) {
            COMPANIES -> {
                itemsIndexed(uiState.companyInfoList) { index, companyInfo ->
                    CompanyInfoCard(companyInfo) { onCompanyClick(index + 1L) }
                }
            }

            VACANCIES -> {
                itemsIndexed(uiState.vacancyInfoList) { index, vacancyInfo ->
                    VacancyCard(vacancyInfo) { onVacancyClick(index + 1L) }
                }
            }
        }


    }
}

@Composable
fun VacancyCard(
    vacancyInfo: com.example.domain.models.VacancyDomainInfo,
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

@Composable
fun CompanyInfoCard(companyInfo: CompanyDomainInfo, onCompanyClick: () -> Unit) {
    val modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
    Card(
        modifier = modifier.clickable { onCompanyClick.invoke() }
    ) {
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.company_name, companyInfo.name)
        )
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.field_of_activity, companyInfo.fieldOfActivity)
        )
    }
}
