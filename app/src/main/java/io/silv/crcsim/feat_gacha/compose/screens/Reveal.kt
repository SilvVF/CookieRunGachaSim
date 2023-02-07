package io.silv.crcsim.feat_gacha.compose.screens

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import io.silv.crcsim.R
import io.silv.crcsim.feat_gacha.CookieDraw
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

    Surface(
        Modifier
            .fillMaxSize()
            .clickable(remember{ MutableInteractionSource() }, null) {
                onNavigate()
            }
    ) {
        Text(text = cookieDraw.toString())
    }
}