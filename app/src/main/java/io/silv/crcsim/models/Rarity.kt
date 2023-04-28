package io.silv.crcsim.models

sealed interface Rarity {
    object Common: Rarity
    object Rare: Rarity
    object Epic: Rarity
    object Special: Rarity
    object Legendary: Rarity

    object Ancient: Rarity
}

fun Rarity.toComparable() = when (this) {
        Rarity.Common -> 1
        Rarity.Rare -> 2
        Rarity.Epic -> 3
        Rarity.Special -> 4
        Rarity.Ancient -> 5
        Rarity.Legendary -> 6
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
