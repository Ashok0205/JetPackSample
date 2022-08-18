package com.example.jetpacksamplev1.main

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacksamplev1.screen.category.CategoryViewModel
import com.example.jetpacksamplev1.screen.home.HomeViewModel
import com.example.jetpacksamplev1.screen.profile.ProfileViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.example.jetpacksamplev1.R
import com.example.jetpacksamplev1.screen.category.CategoryView
import com.example.jetpacksamplev1.screen.home.HomeContent
import com.example.jetpacksamplev1.screen.profile.ProfileView
import com.example.jetpacksamplev1.screen.components.*

@Composable
fun MainContent(
    viewModel: HomeViewModel,
    categoryViewModel: CategoryViewModel,
    profileViewModel: ProfileViewModel,
   // onLoginFragment: (String) -> Unit,
   // onDetails: (Int) -> Unit,
    onExploreClicked: () -> Unit,
) {
    val loading by viewModel.loading.collectAsState()
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(DelishHomeTabs.Home) }
    val tabs = DelishHomeTabs.values()
    LoadingContent(loading) {
        JetPackScaffold(
          ///  backgroundColor = MaterialTheme.colors.primarySurface,
            topBar = {
                HomeTopBar { onExploreClicked() }
            },
            bottomBar = {
                BottomNavigation(backgroundColor = MaterialTheme.colors.primary)  {
                    tabs.forEach { tab ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = null
                                )
                            },
                            label = { Text(text = stringResource(id = tab.title)) },
                            selected = tab == selectedTab,
                            onClick = { setSelectedTab(tab) },
                            alwaysShowLabel = false,
                            selectedContentColor = MaterialTheme.colors.secondary,
                            unselectedContentColor = LocalContentColor.current,
                            modifier = Modifier.navigationBarsPadding(),

                        )
                    }
                }
            }
        ) {
            Box(modifier = Modifier.padding(bottom = 50.dp)) {
                when (selectedTab) {
                    DelishHomeTabs.Home -> HomeContent(viewModel,)
                    DelishHomeTabs.Category -> CategoryView(categoryViewModel, "onDetails")
                    DelishHomeTabs.Profile -> ProfileView(profileViewModel, "")
                }
            }
        }
    }
}

@Composable
fun HomeTopBar(onExploreClicked: () -> Unit) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.dashboard_title),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(8.dp)
            )
        },
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                IconButton(
                    onClick = {
                        onExploreClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Logout,
                        contentDescription = stringResource(R.string.map),
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        },
        elevation = 6.dp
    )
}
@Composable
fun HomeTopBarOld(onExploreClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(8.dp)
            )
        },
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                IconButton(
                    onClick = {
                        onExploreClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Logout,
                        contentDescription = stringResource(R.string.map)
                    )
                }
            }
        },
        elevation = 6.dp
    )
}

enum class DelishHomeTabs(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    Home(R.string.home_tab, Icons.Filled.BlurOn),
    Category(R.string.category_tab, Icons.Filled.BookmarkBorder),
    Profile(R.string.profile_tab, Icons.Filled.EventNote)
}