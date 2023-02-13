package io.silv.crcsim.feat_gacha.compose.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.silv.crcsim.R
import io.silv.crcsim.feat_gacha.CookieDraw
import io.silv.crcsim.feat_gacha.compose.components.CoilGif
import io.silv.crcsim.feat_gacha.compose.components.Player
import io.silv.crcsim.models.Rarity

@Composable
fun RevealScreen(
    cookieDraw: CookieDraw,
    onNavigate: () -> Unit
) {

    val ctx = LocalContext.current

    fun getCookieMediaItemFromRarity(rarity: Rarity): MediaItem =
       MediaItem.fromUri(
           Uri.parse(
               "android.resource://" +
                       ctx.packageName + "/" +
                       when(rarity) {
                           Rarity.Common -> R.raw.common_oven
                           Rarity.Rare -> R.raw.rare_oven
                           Rarity.Epic -> R.raw.epic_oven
                           Rarity.Legendary -> R.raw.epic_oven
                           Rarity.Special -> R.raw.epic_oven
                       }
           )
       )

    fun getSoulstoneMediaItemFromRarity(rarity: Rarity): MediaItem = MediaItem.fromUri(
        Uri.parse(
            "android.resource://" +
                    ctx.packageName + "/" +
                    when(rarity) {
                        Rarity.Common -> R.raw.common_soulstone_oven
                        Rarity.Rare -> R.raw.soulstone_oven_rare
                        Rarity.Epic, Rarity.Legendary, Rarity.Special -> R.raw.soulstone_oven_epic
                    }
        )
    )

    Box(Modifier.clickable(remember{ MutableInteractionSource() }, null) {
        onNavigate()
    }) {
       Player(
           mediaItem = if (cookieDraw.full)
               getCookieMediaItemFromRarity(cookieDraw.cookie.rarity)
           else
               getSoulstoneMediaItemFromRarity(cookieDraw.cookie.rarity),
           playerBlock = {},
           mediaEnd =  {
               onNavigate()
           }
       )
    }
}



@Composable
fun RevealIdleScreen(
    cookieDraw: CookieDraw,
    onNavigate: () -> Unit
) {

    val ctx = LocalContext.current

    Surface(
        Modifier
            .fillMaxSize()
            .clickable(remember { MutableInteractionSource() }, null) {
                onNavigate()
            }
    ) {

        if (cookieDraw.full) {
            Box(Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(ctx)
                        .data(cookieDraw.cookie.gachaBgUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "gacha background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                CoilGif(
                    modifier = Modifier.fillMaxSize(),
                    url = cookieDraw.cookie.imageUrl
                )
            }
        } else {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.soulsstone_common_bg),
                    contentDescription ="soulstone background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                AsyncImage(
                    model = ImageRequest.Builder(ctx)
                        .data(cookieDraw.cookie.soulstoneUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "soulstone image",
                    modifier = Modifier.size(180.dp),
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                )
            }
        }
    }
}