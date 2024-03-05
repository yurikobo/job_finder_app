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
import androidx.compose.material3.Button
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
import com.core.common.UiState
import com.core.common.UiState.Success
import com.core.common.models.CompanyInfo
import com.core.common.models.VacancyInfo
import com.example.kotlincourse.R
import com.example.kotlincourse.models.HomeScreenData
import com.example.kotlincourse.ui.models.TabContentType
import com.example.kotlincourse.ui.models.TabContentType.COMPANIES
import com.example.kotlincourse.ui.models.TabContentType.VACANCIES
import com.example.kotlincourse.ui.models.TabItem


@Composable
fun HomeScreen(
    uiState: UiState<HomeScreenData>,
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
            is Success -> DetailsScreen(
                uiState,
                tabItems[selectedTabIndex].contentType,
                onCompanyClick,
                onVacancyClick
            )

            is UiState.Loading -> LoadingScreen()
            is UiState.Error -> ErrorScreen(uiState.message, retryAction)
        }

    }
}

@Composable
fun LoadingScreen() {
    Text(stringResource(com.feature.company.ui.R.string.now_loading))
}

@Composable
fun ErrorScreen(message: String?, retryAction: () -> Unit) {
    Column {
        message?.let { Text(it) }
        Button(onClick = retryAction) {
            Text(stringResource(com.feature.company.ui.R.string.retry))
        }
    }


}


@Composable
fun DetailsScreen(
    uiState: Success<HomeScreenData>,
    tabType: TabContentType,
    onCompanyClick: (Long) -> Unit,
    onVacancyClick: (Long) -> Unit
) {

    when (tabType) {
        COMPANIES -> {
            val companiesInfoList = uiState.data?.companiesList
            if (companiesInfoList.isNullOrEmpty()) {
                Text(text = "Nothing to show")
            } else {
                LazyColumn {
                    itemsIndexed(companiesInfoList) { index, companyInfo ->
                        CompanyInfoCard(companyInfo) { onCompanyClick(index + 1L) }
                    }
                }
            }
        }

        VACANCIES -> {
            val vacanciesInfoList = uiState.data?.vacanciesList
            if (vacanciesInfoList.isNullOrEmpty()) {
                Text(text = "Nothing to show")
            } else {
                LazyColumn {
                    itemsIndexed(vacanciesInfoList) { index, vacancyInfo ->
                        VacancyCard(vacancyInfo) { onVacancyClick(index + 1L) }
                    }
                }
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


@Composable
fun CompanyInfoCard(companyInfo: CompanyInfo, onCompanyClick: () -> Unit) {
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
