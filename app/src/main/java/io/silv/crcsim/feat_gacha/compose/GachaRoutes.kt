package io.silv.crcsim.feat_gacha.compose

sealed class GachaRoute(val route: String) {

    object Waiting: GachaRoute("waiting")
    object Start: GachaRoute("start")
    object Idle:  GachaRoute("idle")
    object IdleEnd:  GachaRoute( "idle-end")
    object Reveal: GachaRoute( "reveal")
    object End:  GachaRoute("end")
}