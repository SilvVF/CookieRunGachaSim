package io.silv.crcsim.feat_ui_inventory

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.silv.crcsim.feat_cookie_gacha.compose.components.AncientColor
import io.silv.crcsim.feat_cookie_gacha.compose.components.CommonColor
import io.silv.crcsim.feat_cookie_gacha.compose.components.EpicColor
import io.silv.crcsim.feat_cookie_gacha.compose.components.LegendaryColor
import io.silv.crcsim.feat_cookie_gacha.compose.components.RareColor
import io.silv.crcsim.feat_cookie_gacha.compose.components.SuperEpicColor
import io.silv.crcsim.models.Rarity
import io.silv.crcsim.ui.theme.DarkBlueNavRail
import okhttp3.internal.addHeaderLenient
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(
    viewModel: InventoryViewModel = koinViewModel()
) {

    val cookies by viewModel.cookies.collectAsState(initial = emptyList())
    val treasure by viewModel.treasure.collectAsState(initial = emptyList())
    val ctx = LocalContext.current

    LaunchedEffect(key1 = cookies, block = {
        println("cookies $cookies")
    } )

    fun colorFromRarity(rarity: Rarity) = when (rarity) {
            Rarity.Ancient -> AncientColor
            Rarity.Common -> CommonColor
            Rarity.Epic -> EpicColor
            Rarity.Legendary -> LegendaryColor
            Rarity.Rare -> RareColor
            Rarity.Special -> SuperEpicColor
        }


    Surface(Modifier.fillMaxSize(), color = DarkBlueNavRail) {
        Column(Modifier.padding(12.dp)) {
            Row {
                OutlinedTextField(
                    value = viewModel.searchFilter,
                    onValueChange = {viewModel.changeSearchFilter(it) },
                    modifier = Modifier.padding(end = 4.dp),
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(color = Color.White),
                    trailingIcon = {
                        IconButton(onClick = { viewModel.changeSearchFilter("") }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "clear")
                        }
                    },
                    label = {
                        Text(text = "search...")
                    },
                    supportingText = {
                        Text(text = "current filter")
                    }
                )
                Column(Modifier.padding(4.dp)) {
                    LazyRow {
                        items(viewModel.filters) {(name, type)  ->
                            Text(
                                text = name,
                                color = animateColorAsState(
                                    targetValue = if (type == viewModel.typeFilter.collectAsState().value) {
                                        Color.White
                                    } else {
                                        Color.DarkGray
                                    }
                                ).value,
                                modifier = Modifier
                                    .clickable {
                                        viewModel.changeTypeFilter(type)
                                    }
                                    .padding(4.dp)
                            )
                        }
                    }
                    LazyRow {
                        items(viewModel.raritiesList) {(name, rarity) ->
                            Text(
                                text = name,
                                color = animateColorAsState(
                                    targetValue = if (rarity in viewModel.rarityFilter.collectAsState().value){
                                        colorFromRarity(rarity)
                                    } else {
                                        Color.DarkGray
                                    }
                                ).value,
                                modifier = Modifier
                                    .clickable {
                                        viewModel.changeRarityFilter(rarity)
                                    }
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3)
            ) {
                header {
                    Text(text = "Cookies", color = Color.White)
                }
                items(cookies) { ic ->
                    Text(
                        text = "${ic.cookie.id.replace("_", " ")}: ${ic.count}",
                        color = colorFromRarity(ic.cookie.rarity)
                    )
                }
                header {
                    Text(text = "Treasure", color = Color.White)
                }
                items(treasure) {
                    Text(
                        text = "${it.treasure.id.replace("_", " ")}: ${it.count}",
                        color = colorFromRarity(it.treasure.rarity)
                    )
                }
            }
        }
    }
}


fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}