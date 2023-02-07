package io.silv.crcsim.feat_gacha.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.silv.crcsim.R

@Composable
fun CrystalStatusBar(
    modifier: Modifier,
    crystals: Int,
    imageWidth: Float,

) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.7f)
                .align(Alignment.CenterStart)
                .offset((imageWidth / 2).dp)
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    RoundedCornerShape(
                        topEnd = 4.dp,
                        bottomEnd = 4.dp
                    )
                )
                .clip(
                    RoundedCornerShape(
                        topEnd = 4.dp,
                        bottomEnd = 4.dp
                    )
                )
                .background(
                    Color.Black.copy(alpha = 0.25f)
                )
        ) {
            Text(
                text = crystals.toString(),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(
                        start = 12.dp,
                        end = 6.dp,
                        top = 2.dp,
                        bottom = 2.dp
                    )
            )
        }
        Image(
            painter = painterResource(id = R.drawable.crystal_icon),
            contentDescription = "crystal icon",
            Modifier
                .width(imageWidth.dp)
                .fillMaxHeight()
                .align(Alignment.CenterStart)
        )
    }
}