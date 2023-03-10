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
                           Rarity.Common -> R.raw.common_cookie_anim
                           Rarity.Rare -> R.raw.rare_cookie_anim
                           Rarity.Epic, Rarity.Special, Rarity.Legendary
                           -> R.raw.epic_cookie_anim

                       }
           )
       )

    fun getSoulstoneMediaItemFromRarity(rarity: Rarity): MediaItem = MediaItem.fromUri(
        Uri.parse(
            "android.resource://" +
                    ctx.packageName + "/" +
                    when(rarity) {
                        Rarity.Common -> R.raw.common_soulstone_anim
                        Rarity.Rare -> R.raw.rare_soulstone_anim
                        Rarity.Epic, Rarity.Legendary, Rarity.Special
                        -> R.raw.epic_soulstone_anim
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
    imageRequest: ImageRequest,
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
                    model = imageRequest,
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
                    painter = painterResource(
                    when (cookieDraw.cookie.rarity) {
                        Rarity.Common -> R.drawable.commonbg
                        Rarity.Rare -> R.drawable.rarebg
                        else -> R.drawable.epicbg
                    }),
                    contentDescription ="soulstone background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                AsyncImage(
                    model = imageRequest,
                    contentDescription = "soulstone image",
                    modifier = Modifier.size(180.dp),
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                )
            }
        }
    }
}