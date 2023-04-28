package io.silv.crcsim.feat_ui_inventory

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import org.koin.androidx.compose.koinViewModel

@Composable
fun InventoryScreen(
    viewModel: InventoryViewModel = koinViewModel()
) {

    val cookies by viewModel.cookies.collectAsState(initial = emptyList())
    val ctx = LocalContext.current

    LaunchedEffect(key1 = cookies, block = {
        println("cookies $cookies")
    } )

    Surface(Modifier.fillMaxSize()) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(3)
        ) {

            items(cookies) { ic ->
                Text("${ic.cookie.replace("_", " ")} ${ic.count}")
            }
        }
    }
}