@file:OptIn(ExperimentalAnimationApi::class)

package io.silv.crcsim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.silv.crcsim.navigation.AnimatedNavigation
import io.silv.crcsim.navigation.NavItem
import io.silv.crcsim.navigation.Navigations
import io.silv.crcsim.ui.theme.CrcSimTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            val vm = koinViewModel<MainActivityViewModel>()

            CrcSimTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(
                            WindowInsets.systemBars.only(
                                WindowInsetsSides.Vertical
                            )
                        ),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedItem by remember { mutableStateOf(0) }
                    val items = listOf(
                        NavItem(
                            label = "Cookies",
                            selectedIcon = painterResource(id = R.drawable.cookies_selected),
                            unselectedIcon = painterResource(id = R.drawable.cookies_unselected),
                        ),
                        NavItem(
                            label = "Artifacts",
                            selectedIcon = painterResource(id = R.drawable.cookies_selected),
                            unselectedIcon = painterResource(id = R.drawable.cookies_unselected),
                        )
                    )
                    val navController = rememberAnimatedNavController()

                    var navRailVisible by remember {
                        mutableStateOf(true)
                    }

                    Navigations(
                        selectedItem = selectedItem,
                        navItems = items,
                        onSelected = {
                            if (it != selectedItem) {
                                selectedItem = it
                                navController.navigate(items[selectedItem].label)
                            }
                        },
                        navRailVisible = navRailVisible
                    ) {
                        AnimatedNavigation(
                            navController = navController,
                            start = items[0].label,
                        ) { visible ->
                            navRailVisible = visible
                        }
                    }
                }
            }
        }
    }
}
