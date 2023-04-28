package io.silv.crcsim.feat_cookie_gacha.compose

sealed class GachaRoute(val route: String) {

    object Waiting: GachaRoute("waiting")
    object Start: GachaRoute("start")
    object Idle:  GachaRoute("idle")
    object IdleEnd:  GachaRoute( "idle-end")
    data class Reveal(val idx: Int): GachaRoute( "reveal/{idx}")
    data class RevealIdle(val idx: Int): GachaRoute( "reveal-idle/{idx}")
    object End:  GachaRoute("end")
}