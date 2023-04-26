package io.silv.crcsim.feat_artifact_gacha.compose.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import io.silv.crcsim.feat_artifact_gacha.compose.ArtifactRoute
import io.silv.crcsim.navigation.composableFadeAnim
import io.silv.crcsim.navigation.toArtifactDest

fun NavGraphBuilder.artifactHome(
    navController: NavController,
    draw1: () -> Unit,
    draw10: () -> Unit
) {

    composableFadeAnim(
        route = "",
        navController = navController
    ) { _, _ ->
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "Home", modifier = Modifier.align(Alignment.Center))
        }
    }
}