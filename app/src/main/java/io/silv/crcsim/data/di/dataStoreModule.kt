package io.silv.crcsim.data.di

import io.silv.crcsim.data.datastore.UserDataStore
import io.silv.crcsim.data.datastore.UserDataStorePreferences
import io.silv.crcsim.data.datastore.dataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {

    single<UserDataStore> {
        UserDataStorePreferences(
            androidContext().dataStore
        )
    }
}