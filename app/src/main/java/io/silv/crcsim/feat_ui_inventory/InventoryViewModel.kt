package io.silv.crcsim.feat_ui_inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.silv.crcsim.feat_ui_inventory.usecase.InventoryCookieFlow
import io.silv.crcsim.feat_ui_inventory.usecase.InventoryTreasureFlow
import io.silv.crcsim.models.Rarity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InventoryViewModel(
    treasureFlow: InventoryTreasureFlow,
    cookieFlow: InventoryCookieFlow
): ViewModel() {

    private val _cookies = cookieFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _rarityFilter = MutableStateFlow<Rarity?>(null)

    val cookies = combine(
        _rarityFilter,
        _cookies
    ) { filter, cookies ->
        return@combine cookies
    }

    fun changeRarityFilter(rarity: Rarity?) =
        viewModelScope.launch { _rarityFilter.emit(rarity) }
}