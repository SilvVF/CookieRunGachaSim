package io.silv.crcsim.feat_gacha

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import io.silv.crcsim.R
import io.silv.crcsim.feat_gacha.compose.GachaMediaPlayer
import org.koin.androidx.compose.koinViewModel

@Composable
fun GachaScreen(
    navController: NavController,
    viewModel: GachaViewModel = koinViewModel()
) {

    val ctx = LocalContext.current

    var playing by remember {
        mutableStateOf(false)
    }

    val uri = remember {
        Uri.parse(
            "android.resource://" +
                    ctx.packageName + "/" +
                    R.raw.epic_pull
        )
    }

    GachaMediaPlayer(
        modifier = Modifier.fillMaxSize()
            .clickable {
                       playing = !playing
            },
        playing = playing,
        uri = uri,
        onCurrentPositionChange = { positionMillis ->

        }
    )
}