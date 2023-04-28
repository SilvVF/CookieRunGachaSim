package io.silv.crcsim.feat_ui_inventory.di

import io.silv.crcsim.feat_ui_inventory.InventoryViewModel
import io.silv.crcsim.feat_ui_inventory.usecase.InventoryCookieFlow
import io.silv.crcsim.feat_ui_inventory.usecase.InventoryTreasureFlow
import io.silv.crcsim.feat_ui_inventory.usecase.inventoryCookieFlowImpl
import io.silv.crcsim.feat_ui_inventory.usecase.inventoryTreasureFlowImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val inventoryModule = module {

    factory {
        InventoryCookieFlow {
            inventoryCookieFlowImpl(
                cookieDao = get()
            )
        }
    }

    factory {
        InventoryTreasureFlow {
            inventoryTreasureFlowImpl(
                treasureDao = get()
            )
        }
    }
    viewModelOf(::InventoryViewModel)
}