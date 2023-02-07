package io.silv.crcsim.feat_gacha.compose.screens


import Draw10Button
import Draw1Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.silv.crcsim.R
import io.silv.crcsim.feat_gacha.compose.components.CrystalStatusBar
import io.silv.crcsim.feat_gacha.compose.components.Particles
import kotlinx.coroutines.delay

@Composable
fun WaitingScreen(
    crystals: Int = 0,
    onDraw10Click: () -> Unit,
    onDraw1Click: () -> Unit,
) {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        var visible by remember {
            mutableStateOf(true)
        }


        LaunchedEffect(key1 = true) {
            while (true) {
                delay(6000)
                visible = !visible
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.cookie_gacha_waiting),
                contentDescription = "background",
                contentScale = ContentScale.Crop,
                modifier =  Modifier.fillMaxSize()
            )
            Particles(
                modifier = Modifier
                    .fillMaxSize(),
                quantity = 30,
                emoji = ".",
                visible = visible
            )
            Particles(
                modifier = Modifier
                    .fillMaxSize(),
                quantity = 30,
                emoji = ".",
                visible = !visible
            )
            CrystalStatusBar(
                crystals = crystals,
                imageWidth = 38f,
                modifier = Modifier
                    .padding(end = 40.dp)
                    .align(Alignment.TopEnd)
                    .height(40.dp)
                    .fillMaxWidth(0.2f)
            )
            Row(
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(32.dp)){
                Draw1Button(
                    onClick = onDraw1Click,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Draw10Button(
                    onClick = onDraw10Click,
                )
            }
        }
    }
}

