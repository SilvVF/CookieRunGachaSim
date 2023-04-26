package io.silv.crcsim.feat_cookie_gacha.di

import io.silv.crcsim.feat_cookie_gacha.CookieGachaViewModel
import io.silv.crcsim.feat_cookie_gacha.gacha.CookieGachaSim
import io.silv.crcsim.feat_cookie_gacha.gacha.DrawCookiesUseCase
import io.silv.crcsim.feat_cookie_gacha.gacha.FetchArtifactHistoryUseCase
import io.silv.crcsim.feat_cookie_gacha.gacha.FetchCookieHistoryUseCase
import io.silv.crcsim.feat_cookie_gacha.gacha.FetchHistoryUseCase
import io.silv.crcsim.feat_cookie_gacha.gacha.UserDataRepo
import io.silv.crcsim.feat_cookie_gacha.gacha.drawCookiesUseCaseImpl
import io.silv.crcsim.feat_cookie_gacha.gacha.fetchArtifactHistoryUseCaseImpl
import io.silv.crcsim.feat_cookie_gacha.gacha.fetchCookieHistoryUseCaseImpl
import io.silv.crcsim.feat_cookie_gacha.gacha.fetchHistoryUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val gachaModule = module {

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
    factory {
        FetchArtifactHistoryUseCase {
            fetchArtifactHistoryUseCaseImpl(histDao = get())
        }
    }
    factory {
        FetchCookieHistoryUseCase {
            fetchCookieHistoryUseCaseImpl(histDao = get())
        }
    }
    factory {
        FetchHistoryUseCase {
            fetchHistoryUseCaseImpl(histDao = get())
        }
    }
    singleOf(::UserDataRepo)

    viewModelOf(::CookieGachaViewModel)
}