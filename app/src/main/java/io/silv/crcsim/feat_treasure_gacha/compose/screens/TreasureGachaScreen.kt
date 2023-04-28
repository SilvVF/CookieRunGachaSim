
package io.silv.crcsim.feat_treasure_gacha.compose.screens

import CrButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.silv.crcsim.R
import io.silv.crcsim.feat_cookie_gacha.compose.components.CrkInfoButtonCol
import io.silv.crcsim.feat_cookie_gacha.compose.components.CrystalStatusBar
import io.silv.crcsim.feat_cookie_gacha.compose.components.HistoryPopup
import io.silv.crcsim.feat_cookie_gacha.compose.components.MoneySpentStatusBar
import io.silv.crcsim.feat_cookie_gacha.compose.components.Particles
import io.silv.crcsim.feat_cookie_gacha.compose.components.ProbabilityPopup
import io.silv.crcsim.feat_treasure_gacha.compose.TreasureGachaViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import kotlin.math.roundToInt

@Composable
fun TreasureGachaScreen(
    gachaInProgress: (inProgress: Boolean) -> Unit,
) {

    val vm = koinViewModel<TreasureGachaViewModel>()

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
        val history by vm.history.collectAsState(initial = emptyList())
        val state by vm.collectAsState()

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
                painter = painterResource(id = R.drawable.treasure_gacha_bg),
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
                    money = (state.crystals * 0.0995f).roundToInt(),
                    imageWidth = 38f,
                    modifier = Modifier
                        .padding(end = 32.dp)
                        .height(40.dp)
                        .fillMaxWidth(0.17f)
                )
                CrystalStatusBar(
                    crystals = state.crystals,
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
                CrButton(
                    painter = painterResource(id = R.drawable.treasure_draw_one),
                    idleHeight = 67f
                ) {
                    vm.drawArtifacts(1)
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
                changeFilter = {
                    vm.changeFilter(it)
                }
            )
        }
    }
}