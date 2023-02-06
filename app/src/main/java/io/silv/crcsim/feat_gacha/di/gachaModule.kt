package io.silv.crcsim.feat_gacha.di

import io.silv.crcsim.feat_gacha.GachaViewModel
import io.silv.crcsim.feat_gacha.usecases.CookieGachaSim
import io.silv.crcsim.feat_gacha.usecases.DrawCookiesUseCase
import io.silv.crcsim.feat_gacha.usecases.GetPityFlow
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val gachaModule = module {

    factoryOf(::CookieGachaSim)
    factoryOf(::DrawCookiesUseCase)
    factoryOf(::GetPityFlow)

    viewModelOf(::GachaViewModel)
}