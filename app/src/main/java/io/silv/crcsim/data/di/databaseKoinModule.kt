package io.silv.crcsim.data.di

import androidx.room.Room
import io.silv.crcsim.R
import io.silv.crcsim.data.room.CrDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.File
import java.util.concurrent.Callable

val databaseKoinModule = module {

    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            CrDatabase::class.java,
            "cookie-db"
        )
            .createFromAsset("database/crdatabaseinitial.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}