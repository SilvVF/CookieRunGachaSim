package io.silv.crcsim.feat_gacha.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.silv.crcsim.ui.theme.CookieRun

@Composable
fun CrkInfoButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: @Composable BoxScope.() -> Unit,
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .border(1.8.dp, Color.Black, RoundedCornerShape(100.dp))
            .shadow(4.dp, RoundedCornerShape(90.dp))
            .background(Brush.verticalGradient(
                listOf(
                    Color.LightGray.copy(alpha = 0.4f),
                    Color.DarkGray.copy(alpha = 0.8f)
                )
            ))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {

        label()
    }
}

@Preview
@Composable
fun CrkInfoButtonPreview() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        CrkInfoButton(
            modifier = Modifier.size(150.dp, 40.dp),
            onClick = {}
        ) {
            Text(
                text = "probabilities",
                fontFamily = CookieRun,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}