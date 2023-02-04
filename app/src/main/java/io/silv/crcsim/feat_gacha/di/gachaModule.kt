package io.silv.crcsim.feat_gacha.di

import androidx.media3.exoplayer.ExoPlayer
import io.silv.crcsim.feat_gacha.GachaViewModel
import io.silv.crcsim.feat_gacha.usecases.CookieGachaSim
import io.silv.crcsim.feat_gacha.usecases.Draw10UseCase
import io.silv.crcsim.feat_gacha.usecases.PlayGachaRevealAnimation
import io.silv.crcsim.feat_gacha.usecases.PlayGachaStartAnimation
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val gachaModule = module {

    factory {
        ExoPlayer.Builder(androidContext())
            .build()
    }

    factoryOf(::CookieGachaSim)
    factoryOf(::Draw10UseCase)
    factoryOf(::PlayGachaStartAnimation)
    factoryOf(::PlayGachaRevealAnimation)


    viewModelOf(::GachaViewModel)
}