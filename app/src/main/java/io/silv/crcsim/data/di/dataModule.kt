package io.silv.crcsim.data.di

import org.koin.dsl.module

val dataModule = module {

    includes(
        daosKoinModule,
        dataStoreModule
    )
}