package io.silv.crcsim.feat_gacha.compose.screens

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import io.silv.crcsim.R
import io.silv.crcsim.feat_gacha.compose.GachaRoute
import io.silv.crcsim.feat_gacha.compose.components.Player

@Composable
fun IdleScreen(
    onNavigate: (GachaRoute) -> Unit
) {

    val ctx = LocalContext.current

    var currentMillis by remember {
        mutableStateOf(0L)
    }
    Box(
        Modifier.clickable(remember { MutableInteractionSource() }, null) {
            onNavigate(GachaRoute.IdleEnd)
        }
    ) {
        Player(
            mediaItem = MediaItem.fromUri(
                Uri.parse(
                    "android.resource://" +
                            ctx.packageName + "/" +
                            R.raw.idle_use_cookie_cutter
                )
            ),
            contentPosition = { currentMillis = it },
            playerBlock = {
                repeatMode = Player.REPEAT_MODE_ONE
            },
            mediaEnd = {  }
        )
        Text("$currentMillis")
    }
}

@Composable
fun IdleEndScreen(
    onNavigate: (GachaRoute) -> Unit
) {

    val ctx = LocalContext.current

    var currentMillis by remember {
        mutableStateOf(0L)
    }
    Player(
        mediaItem = MediaItem.fromUri(
            Uri.parse(
                "android.resource://" +
                        ctx.packageName + "/" +
                        R.raw.cookie_cutter_use_anim
            )
        ),
        contentPosition = {currentMillis = it}
    ) {
        onNavigate(GachaRoute.Reveal(0))
    }
    Text("$currentMillis")
}