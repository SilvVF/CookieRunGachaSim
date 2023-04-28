package io.silv.crcsim.feat_cookie_gacha.compose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.silv.crcsim.ui.theme.CookieRun

@Composable
fun OverlappingText(
    text: String,
    fontSize: Float,
    modifier: Modifier
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            fontFamily = CookieRun,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
            modifier = modifier
                .offset(
                    x = (-1).dp,
                    y = (1).dp
                )
        )
        Text(
            text = text,
            fontSize = fontSize.sp,
            fontFamily = CookieRun,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = modifier
        )
    }
}