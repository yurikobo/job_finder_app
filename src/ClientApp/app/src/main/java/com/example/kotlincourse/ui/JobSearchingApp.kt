package com.example.kotlincourse.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.kotlincourse.R
import com.example.kotlincourse.ui.screens.CompanyScreen
import com.example.kotlincourse.ui.screens.HomeScreen
import com.example.kotlincourse.ui.screens.VacancyScreen
import kotlinx.coroutines.launch


enum class JobAppScreen(@StringRes val title: Int) {
    START(title = R.string.app_name),
    COMPANY_DETAILS(title = R.string.company_details),
    VACANCY_DETAILS(title = R.string.vacancy_details),
    RESUME_SCREEN(title = R.string.resume_label)
}

data class DrawerItem(
    val icon: ImageVector,
    @StringRes val contentDescription: Int,
    val onItemClick: () -> Unit
)

@Composable
fun JobSearchingApp(
    navController: NavHostController,
    jobSearchingViewModel: JobSearchingViewModel
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        JobAppScreen.valueOf(backStackEntry?.destination?.route ?: JobAppScreen.START.name)

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerContent = listOf(
        DrawerItem(
            icon = Icons.Filled.AccountBox,
            contentDescription = R.string.resume_label,
            onItemClick = { navController.navigate(JobAppScreen.RESUME_SCREEN.name) }
        )
    )
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    modifier = Modifier.padding(30.dp),
                    fontSize = 22.sp,
                    text = "App menu"
                )
                LazyColumn {
                    items(drawerContent) { drawerItem ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    drawerItem.onItemClick
                                    scope.launch { drawerState.apply { close() } }
                                }
                                .padding(16.dp)
                        ) {
                            Icon(
                                imageVector = drawerItem.icon,
                                contentDescription = stringResource(id = drawerItem.contentDescription)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = stringResource(id = drawerItem.contentDescription)
                            )
                        }
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                JobAppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() },
                    onMenuButtonCLick = {
                        scope.launch {
                            drawerState.apply { if (isClosed) open() else close() }
                        }
                    }
                )
            }
        ) {


            NavHost(
                navController = navController,
                startDestination = JobAppScreen.START.name,
                modifier = Modifier
                    .fillMaxSize()
//                .verticalScroll(rememberScrollState())
                    .padding(it)
            ) {
                composable(route = JobAppScreen.START.name) {
                    LaunchedEffect(true) {
                        jobSearchingViewModel.currentScreen = JobAppScreen.START
                        jobSearchingViewModel.getScreenInfo()
                    }
                    HomeScreen(
                        uiState = jobSearchingViewModel.screenUiState,
                        retryAction = jobSearchingViewModel::getScreenInfo,
                        onCompanyClick = { companyId ->
                            jobSearchingViewModel.companyId = companyId
                            navController.navigate(JobAppScreen.COMPANY_DETAILS.name)
                        },
                        onVacancyClick = { vacancyId ->
                            jobSearchingViewModel.vacancyId = vacancyId
                            navController.navigate(JobAppScreen.VACANCY_DETAILS.name)
                        }
                    )
                }

                composable(route = JobAppScreen.COMPANY_DETAILS.name) {
                    LaunchedEffect(true) {
                        jobSearchingViewModel.currentScreen = JobAppScreen.COMPANY_DETAILS
                        jobSearchingViewModel.getScreenInfo()
                    }

                    CompanyScreen(
                        uiState = jobSearchingViewModel.screenUiState,
                        retryAction = jobSearchingViewModel::getScreenInfo,
                        onVacancyClick = { vacancyId ->
                            jobSearchingViewModel.vacancyId = vacancyId
                            navController.navigate(JobAppScreen.VACANCY_DETAILS.name)
                        }
                    )
                }
                composable(route = JobAppScreen.VACANCY_DETAILS.name) {
                    LaunchedEffect(true) {
                        jobSearchingViewModel.currentScreen = JobAppScreen.VACANCY_DETAILS
                        jobSearchingViewModel.getScreenInfo()
                    }
                    VacancyScreen(
                        uiState = jobSearchingViewModel.screenUiState,
                        retryAction = jobSearchingViewModel::getScreenInfo,
                        onCompanyClick = {
                            if (backStackEntry?.destination?.route == JobAppScreen.COMPANY_DETAILS.name) {
                                navController.navigateUp()
                            } else {
                                navController.navigate(JobAppScreen.COMPANY_DETAILS.name)
                            }
                        }
                    )
                }

                composable(route = JobAppScreen.RESUME_SCREEN.name) {

                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobAppBar(
    currentScreen: JobAppScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    onMenuButtonCLick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(id = currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            } else {
                IconButton(onClick = onMenuButtonCLick) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = stringResource(id = R.string.menu_button)
                    )

                }
            }
        }
    )
}


