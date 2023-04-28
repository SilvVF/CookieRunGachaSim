package io.silv.crcsim.feat_cookie_gacha.compose.screens


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
import io.silv.crcsim.feat_cookie_gacha.HistoryFilter
import io.silv.crcsim.feat_cookie_gacha.compose.components.*
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun WaitingScreen(
    crystals: Int = 0,
    history: List<List<Pair<String, Int>>>,
    filter: HistoryFilter,
    changeFilter: (filter: HistoryFilter) -> Unit,
    onDraw10Click: () -> Unit,
    onDraw1Click: () -> Unit,
) {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        var particleVisibility by remember {
            mutableStateOf(true)
        }
        var probabilityVisible by remember {
            mutableStateOf(false )
        }
        var historyVisible by remember {
            mutableStateOf(false)
        }


        LaunchedEffect(key1 = true) {
            while (true) {
                delay(6000)
                particleVisibility = !particleVisibility
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
                modifier = Modifier.fillMaxSize(),
                quantity = 30,
                emoji = ".",
                visible = particleVisibility
            )
            Particles(
                modifier = Modifier.fillMaxSize(),
                quantity = 30,
                emoji = ".",
                visible = !particleVisibility
            )
            Row(
                Modifier
                    .align(Alignment.TopEnd)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                MoneySpentStatusBar(
                    money = (crystals * 0.0995f).roundToInt(),
                    imageWidth = 38f,
                    modifier = Modifier
                        .padding(end = 32.dp)
                        .height(40.dp)
                        .fillMaxWidth(0.17f)
                )
                CrystalStatusBar(
                    crystals = crystals,
                    imageWidth = 38f,
                    modifier = Modifier
                        .padding(end = 32.dp)
                        .height(40.dp)
                        .fillMaxWidth(0.2f)
                )
            }
            CrkInfoButtonCol(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(32.dp)
                    .offset(y = (-80).dp),
                onProbabilityClick = { probabilityVisible = true },
                onGachaHistoryClick = { historyVisible = true }
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(32.dp), contentAlignment = Alignment.BottomEnd) {
                Draw1Button(onClick = onDraw1Click)
            }
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                Box(modifier = Modifier.padding(end  = 186.dp, bottom = 32.dp)) {
                    Draw10Button(onClick = onDraw10Click)
                }
            }
            ProbabilityPopup(
                show = probabilityVisible,
                onDismissRequest = { probabilityVisible = false }
            )
            HistoryPopup(
                history = history,
                show = historyVisible,
                onDismissRequest = { historyVisible = false },
                changeFilter = changeFilter,
                filter = filter
            )
        }
    }
}

