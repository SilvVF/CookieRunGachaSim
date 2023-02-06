package io.silv.crcsim.feat_gacha

import android.provider.MediaStore.Video

sealed interface GachaPhase {
    /**
     * Object Representing the phase before the button to start a gacha pull is clicked
     */
    object Waiting: GachaPhase

    /**
     * This represents the part from the witches house to the initial reveal of the cookies obtained
     */
    object StartAnimation: GachaPhase
    object Started: GachaPhase

    object RevealAnimation: GachaPhase
    object Reveal: GachaPhase

    object End: GachaPhase
}
