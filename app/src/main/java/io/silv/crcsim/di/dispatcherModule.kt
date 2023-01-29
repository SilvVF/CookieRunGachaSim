package io.silv.crcsim.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

interface CrkDispatcher {
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

val dispatcherModule = module {

    single<CrkDispatcher> {
        object: CrkDispatcher {
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO
            override val default: CoroutineDispatcher
                get() = Dispatchers.Default
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val unconfined: CoroutineDispatcher
                get() = Dispatchers.Unconfined

        }
    }
}