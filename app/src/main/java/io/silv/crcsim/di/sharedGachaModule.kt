package io.silv.crcsim.di

import io.silv.crcsim.feat_cookie_gacha.di.cookieGachaModule
import io.silv.crcsim.feat_cookie_gacha.domain.FetchArtifactHistoryUseCase
import io.silv.crcsim.feat_cookie_gacha.domain.FetchCookieHistoryUseCase
import io.silv.crcsim.feat_cookie_gacha.domain.FetchHistoryUseCase
import io.silv.crcsim.feat_cookie_gacha.domain.UserDataRepo
import io.silv.crcsim.feat_cookie_gacha.domain.fetchArtifactHistoryUseCaseImpl
import io.silv.crcsim.feat_cookie_gacha.domain.fetchCookieHistoryUseCaseImpl
import io.silv.crcsim.feat_cookie_gacha.domain.fetchHistoryUseCaseImpl
import io.silv.crcsim.feat_treasure_gacha.di.treasureGachaModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedGachaModule = module {
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

    includes(cookieGachaModule, treasureGachaModule)
}