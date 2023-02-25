package io.silv.crcsim.feat_gacha.compose.screens

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import io.silv.crcsim.R
import io.silv.crcsim.feat_gacha.compose.GachaRoute
import io.silv.crcsim.feat_gacha.compose.components.Player


@Composable
fun StartScreen(
    onNavigate: (GachaRoute) -> Unit
) {

    val ctx = LocalContext.current

    var currentMillis by remember {
        mutableStateOf(0L)
    }
    Box(Modifier
        .clickable(remember { MutableInteractionSource() }, null) {
            onNavigate(GachaRoute.Idle)
        }
    ) {
        Player(
            mediaItem = remember {
                MediaItem.fromUri(
                    Uri.parse(
                        "android.resource://" +
                                ctx.packageName + "/" +
                                R.raw.cookie_gacha_intro_anim
                    )
                )
            },
            contentPosition = { currentMillis = it }
        ) {
            onNavigate(GachaRoute.Idle)
        }
        Text(
            remember(currentMillis) {
                derivedStateOf { currentMillis.toString() }.value
            }
        )
        Button(onClick = { onNavigate(GachaRoute.End) }, Modifier.align(Alignment.TopEnd)) {
            Text(text = "Skip")
        }
    }
}