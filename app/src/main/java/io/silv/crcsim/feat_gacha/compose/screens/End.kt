package io.silv.crcsim.feat_gacha.compose.screens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.silv.crcsim.feat_gacha.CookieDrawResult
import io.silv.crcsim.feat_gacha.compose.components.CoilGif
import io.silv.crcsim.ui.theme.CookieRun
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
                OverlappingText()
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalArrangement = Arrangement.Center
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
                            }
                        }
                    }
                }
        }
    }
}

@Composable
private fun OverlappingText() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Results",
            fontFamily = CookieRun,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 42.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(4.dp)
                .offset(
                    x = (-1).dp,
                    y = (1).dp
                )
        )
        Text(
            text = "Results",
            fontFamily = CookieRun,
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp,
            color = Color.White,
            modifier = Modifier.padding(4.dp)
        )
    }
}