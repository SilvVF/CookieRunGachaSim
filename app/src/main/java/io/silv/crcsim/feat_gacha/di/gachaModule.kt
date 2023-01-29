package io.silv.crcsim.feat_gacha

import io.silv.crcsim.feat_gacha.usecases.CookieGachaSim
import io.silv.crcsim.feat_gacha.usecases.Draw10UseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val gachaModule = module {


    factoryOf(::CookieGachaSim)
    factoryOf(::Draw10UseCase)

    viewModelOf(::GachaViewModel)
}