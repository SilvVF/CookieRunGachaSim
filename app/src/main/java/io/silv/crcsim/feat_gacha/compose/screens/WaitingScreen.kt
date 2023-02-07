package io.silv.crcsim.feat_gacha.compose.screens


import Draw10Button
import Draw1Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.silv.crcsim.feat_gacha.compose.GachaRoute

@Composable
fun WaitingScreen(
    onDraw10Click: () -> Unit,
    onDraw1Click: () -> Unit,
) {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Draw10Button(onClick = onDraw10Click)
            Draw1Button(onClick = onDraw1Click)
        }
    }
}