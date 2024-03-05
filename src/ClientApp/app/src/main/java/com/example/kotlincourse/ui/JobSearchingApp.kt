package com.example.kotlincourse.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.core.common.navigation.NavigationConstants
import com.example.kotlincourse.R
import com.example.kotlincourse.navigation.AppNavController
import com.example.kotlincourse.navigation.NavigationProvider
import kotlinx.coroutines.launch

data class DrawerItem(
    val icon: ImageVector,
    @StringRes val contentDescription: Int,
    val onItemClick: () -> Unit
)

@Composable
fun JobSearchingApp(
    navController: NavHostController,
    jobSearchingViewModel: JobSearchingViewModel,
    navigationProvider: NavigationProvider
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        backStackEntry?.destination?.route ?: NavigationConstants.HOME_SCREEN.nestedRoute

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerContent = listOf(
        DrawerItem(
            icon = Icons.Filled.AccountBox,
            contentDescription = R.string.resume_label,
            onItemClick = { }
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
                    text = stringResource(R.string.app_menu)
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
            AppNavController(
                jobSearchingViewModel = jobSearchingViewModel,
                navController = navController,
                navigationProvider = navigationProvider,
                modifier = Modifier.padding(it)
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobAppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    onMenuButtonCLick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = currentScreen) },
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



