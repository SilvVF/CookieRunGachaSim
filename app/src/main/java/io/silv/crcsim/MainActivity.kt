package io.silv.crcsim

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.silv.crcsim.feat_gacha.GachaViewModel
import io.silv.crcsim.feat_gacha.compose.GachaMediaPlayer
import io.silv.crcsim.ui.theme.CrcSimTheme
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import org.orbitmvi.orbit.syntax.simple.intent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {

            val vm = getViewModel<MainActivityViewModel>()
            vm.collectSideEffect {

            }

            CrcSimTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val gachaViewModel = getViewModel<GachaViewModel>()
                    val state by gachaViewModel.collectAsState()
                    var playing by remember {
                        mutableStateOf(false)
                    }

                    LazyColumn(Modifier.fillMaxSize()) {
                        item {
                            Button(onClick = {
                                playing = !playing
                                gachaViewModel.draw10Cookies()
                            }) {
                                Text(text = "Draw 10")
                            }
                            Text(text = state.total.toString())
                        }
                        item {

                            val uri = remember {
                                Uri.parse(
                                    "android.resource://" +
                                            packageName + "/" +
                                            R.raw.epic_pull
                                )
                            }

                            GachaMediaPlayer(
                                uri = uri,
                                modifier = Modifier.fillMaxWidth().height(300.dp),
                                onCurrentPositionChange = {},
                                playing = playing
                            )
                        }
                        items(state.cookies) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(text = it.toString())
                            }
                        }
                        items(state.db) {
                            Text(text = it)
                        }
                    }
                }
            }
        }
    }
}
