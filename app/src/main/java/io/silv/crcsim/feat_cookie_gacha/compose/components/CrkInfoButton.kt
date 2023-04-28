package io.silv.crcsim.feat_cookie_gacha.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            .border(1.dp, Color.Black, RoundedCornerShape(100.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.LightGray.copy(alpha = 0.2f),
                        Color.LightGray.copy(alpha = 0.1f)
                    )
                )
            )
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

@Composable
fun CrkInfoButtonCol(
    modifier: Modifier = Modifier,
    onProbabilityClick: () -> Unit,
    onGachaHistoryClick: () -> Unit
) {

    Column(
        modifier = modifier
    ) {
        CrkInfoButton(
            modifier = Modifier.width(140.dp),
            onClick = { onProbabilityClick() }
        ) {
            OverlappingText(
                text = "Probabilities",
                fontSize = 14f,
                modifier = Modifier.padding(4.dp)
            )
        }
        Spacer(Modifier.height(12.dp))
        CrkInfoButton(
            modifier = Modifier.width(140.dp),
            onClick = { onGachaHistoryClick() }
        ) {
            OverlappingText(
                text = "Gacha History",
                fontSize = 14f,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}