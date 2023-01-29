@file:OptIn(ExperimentalAnimationApi::class)

package io.silv.crcsim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.silv.crcsim.navigation.AnimatedNavigation
import io.silv.crcsim.navigation.Navigations
import io.silv.crcsim.ui.theme.CrcSimTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val vm = getViewModel<MainActivityViewModel>()

            CrcSimTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedItem by remember { mutableStateOf(0) }
                    val items = listOf("Gacha", "Search", "Settings")
                    val icons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)
                    val navController = rememberAnimatedNavController()

                    Navigations(
                        selectedItem = selectedItem,
                        navItems = items.zip(icons),
                        onSelected = { selectedItem = it }
                    ) {
                        AnimatedNavigation(
                            navController = navController,
                            start = items[0]
                        )
                    }
                }
            }
        }
    }
}
