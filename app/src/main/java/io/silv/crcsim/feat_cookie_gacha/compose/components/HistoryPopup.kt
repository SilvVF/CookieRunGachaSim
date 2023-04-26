package io.silv.crcsim.feat_cookie_gacha.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import io.silv.crcsim.feat_cookie_gacha.container.HistoryFilter

@Composable
fun HistoryPopup(
    history: List<List<Pair<String, Int>>>,
    show: Boolean,
    changeFilter: (filter: HistoryFilter) -> Unit,
    onDismissRequest: () -> Unit,
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

        var dropdownExpanded by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight()
                .applyCrPopupBg(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(Modifier.fillMaxWidth()) {
                OverlappingText(
                    text = "Cookie Gacha History",
                    fontSize = 32f,
                    modifier = Modifier
                )
                IconButton(
                    onClick = { dropdownExpanded = !dropdownExpanded }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "history filter",
                        modifier = Modifier.align(Alignment.CenterEnd),
                    )
                }
                DropdownMenu(expanded = dropdownExpanded, onDismissRequest = { dropdownExpanded = false }, offset = DpOffset(x = 140.dp, y = 0.dp)) {
                    val items = listOf<Pair<String, HistoryFilter>>(
                        "None" to HistoryFilter.None, "Cookies" to HistoryFilter.Cookie, "Artifacts" to HistoryFilter.Artifact,
                    )
                    items.forEach { (name, filter) ->
                        DropdownMenuItem(
                            text = { Text(name) },
                            onClick = {
                                changeFilter(filter)
                                dropdownExpanded = false
                            }
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(ProbUnderTextColor),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(history) {historyItem ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(0.9f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(ProbabilityPopupBgColor)) {
                        historyItem.chunked(3).forEach { chunk ->
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    horizontalArrangement = Arrangement.Center,
                                ) {
                                    chunk.forEach { (item, amount) ->
                                        Text("$item - x$amount")
                                    }
                                }
                            }
                        }
                }
            }
        }
    }
}