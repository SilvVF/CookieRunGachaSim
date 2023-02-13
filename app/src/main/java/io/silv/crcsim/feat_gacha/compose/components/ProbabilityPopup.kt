package io.silv.crcsim.feat_gacha.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

@Composable
fun ProbabilityPopup(
    show: Boolean
) {
    Popup(
        alignment = Alignment.Center
    ) {

        Column(
            Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.4f)
                .clip(
                    RoundedCornerShape(
                        topEnd = 32.dp,
                        topStart = 32.dp
                    )
                )
        ) {

        }
    }
}