package io.silv.crcsim.data.di

import androidx.room.Room
import io.silv.crcsim.data.room.CrDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseKoinModule = module {

    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            CrDatabase::class.java,
            "cookie-db"
        )
            .createFromAsset("database/crdatabaseinitial.db")
            .build()
    }

}