package io.silv.crcsim.di


import io.silv.crcsim.MainActivityViewModel
import io.silv.crcsim.data.di.dataModule
import io.silv.crcsim.feat_gacha.gachaModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val appModule = module {

    includes(
        dispatcherModule,
        dataModule,
        gachaModule,
    )


    viewModelOf(::MainActivityViewModel)
}
