package io.silv.crcsim.feat_cookie_gacha.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

val ProbPopupHighlight = Color(0xff535C89)
val ProbabilityPopupBgColor = Color(0xff3B456C)
val ProbUnderTextColor = Color(0xff2E2F3D)
val AncientColor = Color(0xff7F66CA)
val LegendaryColor = Color(0xff408365)
val SuperEpicColor = Color(0xff9E69B0)
val EpicColor = Color(0xffB86E7E)
val RareColor = Color(0xff527789)
val CommonColor = Color(0xffA8907A)

@Composable
fun ProbabilityPopup(
    show: Boolean,
    onDismissRequest: () -> Unit
) {
    if (!show) {
        return
    }
    Popup(
        alignment = Alignment.Center,
        onDismissRequest = {
            onDismissRequest()
        },
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.4f)
                .applyCrPopupBg(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OverlappingText(
                text = "Cookie Gacha Probabilities",
                fontSize = 32f,
                modifier = Modifier
            )
            Row(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.8f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(ProbUnderTextColor)
            ) {

            }
        }
    }
}

fun Modifier.applyCrPopupBg() = this.clip(
    RoundedCornerShape(32.dp)
)
    .background(ProbabilityPopupBgColor)
    .drawWithContent {
        drawRect(
            brush = Brush.verticalGradient(
                listOf(ProbPopupHighlight, ProbPopupHighlight)
            ),
            topLeft = Offset(0f, 0f),
            size = Size(width = size.width, height = 12f)
        )
        rotate(40f, Offset.Zero) {
            drawOval(
                brush = Brush.verticalGradient(
                    listOf(ProbPopupHighlight, ProbPopupHighlight)
                ),
                topLeft = Offset(
                    size.width * 0.02f,
                    -20f
                ),
                size = Size(width = 35f, height = 60f)
            )
        }
        drawContent()
    }
    .border(1.dp, Color.Black, RoundedCornerShape(32.dp))
@Composable
fun ProbabilityListItem() {

}

@Preview()
@Composable
fun ProbabilityPopupPreview() {

    Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
        ProbabilityPopup(show = true) {

        }
    }
}