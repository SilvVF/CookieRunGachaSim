import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.silv.crcsim.R
import kotlinx.coroutines.launch
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.painter.Painter
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

@Composable
fun CrButton(
    painter: Painter,
    idleHeight: Float,
    idleWidth: Float = idleHeight * 2,
    endWidth: Float = idleWidth + 10f,
    endHeight: Float = idleHeight + 10f,
    onClick: () -> Unit
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }

    val pressed = interactionSource.collectIsPressedAsState().value

    val scope = rememberCoroutineScope()

    val width = remember {
        Animatable(idleWidth)
    }
    val height = remember {
        Animatable(idleHeight)
    }

    LaunchedEffect(key1 = pressed) {
        if (pressed) {
            launch { width.animateTo(endWidth) }
            launch { height.animateTo(endHeight) }
        } else {
            launch { width.animateTo(idleWidth) }
            launch { height.animateTo(idleHeight) }
        }
    }


    fun playAnim() = scope.launch {
        val w = async { width.animateTo((idleWidth + endWidth) / 2) }
        val h = async { height.animateTo((idleHeight + endHeight) / 2) }
        awaitAll(h, w)
        if (!pressed) {
            launch { width.animateTo(idleWidth) }
            launch{ height.animateTo(idleHeight) }
        }
    }

    Image(
        painter = painter,
        contentDescription = "Draw",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .width(width.value.dp)
            .height(height.value.dp)
            .border(
                2.dp,
                Color.Black.copy(alpha = 0.8f),
                RoundedCornerShape(22)
            )
            .clickable(interactionSource, null) {
                scope.launch {
                    launch { onClick() }
                    launch { playAnim() }
                }
            }
    )
}

@Composable
fun Draw1Button(
    onClick: () -> Unit,
    height: Float = 67f
) {
    CrButton(
        painter = painterResource(id = R.drawable.draw1),
        idleHeight = height
    ) {
        onClick()
    }
}

@Composable
fun Draw10Button(
    onClick: () -> Unit,
    height: Float = 67f
) {
    CrButton(
        painter = painterResource(id = R.drawable.draw10),
        idleHeight = height
    ) {
        onClick()
    }
}

@Preview
@Composable
fun CrButtonPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Draw10Button(onClick = { /*TODO*/ })
        Spacer(modifier = Modifier.height(30.dp))
        Draw1Button(onClick = { /*TODO*/ })
    }
}