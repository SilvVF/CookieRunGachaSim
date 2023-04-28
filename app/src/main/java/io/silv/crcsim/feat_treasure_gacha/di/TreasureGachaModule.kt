package io.silv.crcsim.feat_treasure_gacha.di

import io.silv.crcsim.feat_treasure_gacha.compose.TreasureGachaViewModel
import io.silv.crcsim.feat_treasure_gacha.domain.DrawTreasureUseCase
import io.silv.crcsim.feat_treasure_gacha.domain.drawTreasureUseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val treasureGachaModule = module {

    factory {
        DrawTreasureUseCase {
            drawTreasureUseCase(
                amount = it,
                treasureDao = get(),
                histDao = get(),
                dataStore = get()
            )
        }
    }

    viewModelOf(::TreasureGachaViewModel)
}
