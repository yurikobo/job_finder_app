package com.feature.resume.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation.NavigationConstants
import com.feature.resume.ui.models.ResumeUiMode
import com.feature.resume.ui.screen.ResumeScreen
import com.feature.resume.ui.screen.ResumeViewModel
import javax.inject.Inject

internal class InternalResumeFeatureApi @Inject constructor(
    private val resumeViewModel: ResumeViewModel
) : ResumeApi {

    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {

        navGraphBuilder.navigation(
            startDestination = NavigationConstants.RESUME_SCREEN.screenRoute,
            route = NavigationConstants.RESUME_SCREEN.nestedRoute
        ) {
            val resumeUiMode = mutableStateOf(ResumeUiMode.VIEW)
            composable(NavigationConstants.RESUME_SCREEN.screenRoute) {
                LaunchedEffect(
                    key1 = true,
                    block = {
                        resumeViewModel.getScreenInfo()
                    }
                )
                Scaffold(
                    topBar = {
                        ResumeAppBar(
                            screenMode = resumeUiMode,
                            currentScreen = NavigationConstants.RESUME_SCREEN.screenRoute,
                            canNavigateBack = navController.previousBackStackEntry != null,
                            navigateUp = { navController.navigateUp() },
                            onSaveChanges = resumeViewModel::updateResume,
                            onDiscardChanges = resumeViewModel::getScreenInfo
                        )
                    }
                ) {
                    ResumeScreen(
                        uiState = resumeViewModel.screenUiState,
                        onRetry = resumeViewModel::getScreenInfo,
                        modifier = Modifier.padding(it),
                        screenMode = resumeUiMode
                    )
                }
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumeAppBar(
    screenMode: MutableState<ResumeUiMode>,
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    onSaveChanges: () -> Unit,
    onDiscardChanges: () -> Unit,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        title = { Text(text = currentScreen) },
        modifier = modifier,
        navigationIcon = {
            when (screenMode.value) {
                ResumeUiMode.VIEW -> {
                    if (canNavigateBack) {
                        IconButton(onClick = navigateUp) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "test"
                            )
                        }
                    }
                }

                ResumeUiMode.EDIT -> {
                    IconButton(onClick = {
                        onDiscardChanges.invoke()
                        screenMode.value = ResumeUiMode.VIEW
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "test"
                        )
                    }
                }
            }
        },
        actions = {
            when (screenMode.value) {
                ResumeUiMode.VIEW -> {
                    IconButton(onClick = { screenMode.value = ResumeUiMode.EDIT }) {
                        Icon(
                            imageVector = Icons.Filled.Create,
                            contentDescription = "test1"
                        )
                    }
                }

                ResumeUiMode.EDIT -> {
                    IconButton(onClick = {
                        onSaveChanges.invoke()
                        screenMode.value = ResumeUiMode.VIEW
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "test1"
                        )
                    }
                }
            }
        }
    )
}



