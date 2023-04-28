package io.silv.crcsim.data.di

import io.silv.crcsim.data.room.CrDatabase
import org.koin.dsl.module

val daosKoinModule = module {

    includes(databaseKoinModule)

    single { get<CrDatabase>().cookieDao() }
    single { get<CrDatabase>().treasureDao() }
    single { get<CrDatabase>().pullHistDao() }
}