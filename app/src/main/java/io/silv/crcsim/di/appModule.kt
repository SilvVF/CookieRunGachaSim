package io.silv.crcsim.di


import io.silv.crcsim.MainActivityViewModel
import io.silv.crcsim.data.di.dataModule
import io.silv.crcsim.feat_ui_inventory.di.inventoryModule
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {

    includes(
        dispatcherModule,
        dataModule,
        inventoryModule,
        sharedGachaModule
    )


    viewModelOf(::MainActivityViewModel)
}
