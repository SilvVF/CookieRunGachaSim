package io.silv.crcsim.feat_gacha.di

import io.silv.crcsim.feat_gacha.GachaViewModel
import io.silv.crcsim.feat_gacha.usecases.*
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val gachaModule = module {

    factoryOf(::CookieGachaSim)
    factory {
        DrawCookiesUseCase { pity, amount ->
            drawCookiesUseCaseImpl(
                get(), get(), get(), pity, amount
            )
        }
    }
    singleOf(::UserDataRepo)

    viewModelOf(::GachaViewModel)
}