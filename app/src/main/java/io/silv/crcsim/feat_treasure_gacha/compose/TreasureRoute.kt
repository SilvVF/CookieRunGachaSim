package io.silv.crcsim.feat_treasure_gacha.compose

sealed class TreasureRoute(val route: String) {

    object Waiting: TreasureRoute("waiting")
    object Start: TreasureRoute("start")
    data class Reveal(val idx: Int): TreasureRoute( "reveal/{idx}")
    data class RevealIdle(val idx: Int): TreasureRoute( "reveal-idle/{idx}")
    object End:  TreasureRoute("end")
}