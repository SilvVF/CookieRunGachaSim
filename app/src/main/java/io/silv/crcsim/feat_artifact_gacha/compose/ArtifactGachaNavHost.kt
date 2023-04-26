@file:OptIn(ExperimentalAnimationApi::class)

package io.silv.crcsim.feat_artifact_gacha.compose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.silv.crcsim.feat_artifact_gacha.ArtifactGachaViewModel
import io.silv.crcsim.feat_artifact_gacha.compose.screens.artifactHome
import io.silv.crcsim.navigation.composableFadeAnim
import io.silv.crcsim.navigation.toArtifactDest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArtifactGachaNavHost(
    gachaInProgress: (inProgress: Boolean) -> Unit
) {

    val navController = rememberAnimatedNavController()
    val vm = koinViewModel<ArtifactGachaViewModel>()

    AnimatedNavHost(
        navController = navController,
        startDestination = ""
    ) {

        artifactHome(
            navController = navController,
            draw1 = {
                vm.drawArtifacts(1)
                navController.toArtifactDest(ArtifactRoute.Start)
            },
            draw10 = {
                vm.drawArtifacts(10)
                navController.toArtifactDest(ArtifactRoute.Start)
            }
        )
    }
}