package io.silv.crcsim.feat_cookie_gacha.di

import io.silv.crcsim.feat_cookie_gacha.compose.CookieGachaViewModel
import io.silv.crcsim.feat_cookie_gacha.domain.CookieGachaSim
import io.silv.crcsim.feat_cookie_gacha.domain.DrawCookiesUseCase
import io.silv.crcsim.feat_cookie_gacha.domain.drawCookiesUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val cookieGachaModule = module {

    factoryOf(::CookieGachaSim)
    factory {
        DrawCookiesUseCase { pity, amount ->
            drawCookiesUseCaseImpl(
                cookieDao = get(),
                pullHistDao = get(),
                gacha = get(),
                userDataStore = get(),
                pity,
                amount
            )
        }
    }
    viewModelOf(::CookieGachaViewModel)
}