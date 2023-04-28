package io.silv.crcsim.feat_cookie_gacha.compose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.silv.crcsim.feat_cookie_gacha.CookieDrawResult
import io.silv.crcsim.feat_cookie_gacha.compose.components.CoilGif
import io.silv.crcsim.feat_cookie_gacha.compose.components.OverlappingText
import io.silv.crcsim.ui.theme.DarkBlueNavRail

@Composable
fun EndScreen(
    pull: CookieDrawResult,
    imageList:  List<ImageRequest>,
    onNavigate: () -> Unit
) {

    Surface(
        Modifier
            .fillMaxSize()
            .clickable(remember { MutableInteractionSource() }, null) {
                onNavigate()
            },
        color = DarkBlueNavRail
    ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                OverlappingText(text = "Results", 42f, Modifier.padding(4.dp))
                LazyHorizontalGrid(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalArrangement = Arrangement.Center,
                    rows = GridCells.Fixed(2)
                ) {
                    itemsIndexed(pull.result) { idx, res ->
                        if (res.full) {
                            Box(modifier = Modifier.aspectRatio(1f)) {
                                CoilGif(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .fillMaxSize()
                                        .align(Alignment.Center),
                                    url = res.cookie.imageUrl
                                )
                                CookieRarityTag(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(0.2f)
                                        .align(Alignment.BottomCenter)
                                        .padding(bottom = 12.dp),
                                    rarity = res.cookie.rarity
                                )
                            }
                        } else {
                            Box(modifier = Modifier.aspectRatio(1f)) {
                                AsyncImage(
                                    model = imageList[idx],
                                    contentDescription = res.cookie.name,
                                    modifier = Modifier
                                        .padding(32.dp)
                                        .fillMaxSize()
                                        .align(Alignment.Center)
                                )
                                Text(
                                    text = "x${res.count}",
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .padding(bottom = 12.dp, end = 12.dp)
                                )
                            }
                        }
                    }
                }
        }
    }
}