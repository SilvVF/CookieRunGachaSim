package io.silv.crcsim.models

sealed interface Rarity {
    object Common: Rarity
    object Rare: Rarity
    object Epic: Rarity
    object Special: Rarity
    object Legendary: Rarity

    object Ancient: Rarity
}

fun Rarity.string(): String {
    return when (this) {
        Rarity.Rare -> "r"
        Rarity.Epic -> "e"
        Rarity.Special -> "s"
        Rarity.Ancient -> "a"
        Rarity.Legendary -> "l"
        else -> "c"
    }
}
