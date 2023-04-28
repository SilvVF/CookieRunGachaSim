package io.silv.crcsim.feat_ui_inventory

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.silv.crcsim.feat_ui_inventory.usecase.InventoryCookieFlow
import io.silv.crcsim.feat_ui_inventory.usecase.InventoryTreasureFlow
import io.silv.crcsim.models.Rarity
import io.silv.crcsim.models.toComparable
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class InventoryViewModel(
    treasureFlow: InventoryTreasureFlow,
    cookieFlow: InventoryCookieFlow
): ViewModel() {


    private val _rarityFilter = MutableStateFlow<Set<Rarity>>(
        setOf(Rarity.Epic, Rarity.Rare, Rarity.Special, Rarity.Legendary, Rarity.Ancient, Rarity.Common)
    )
    val rarityFilter = _rarityFilter.asStateFlow()

    private val _typeFilter = MutableStateFlow<InventoryTypeFilter>(InventoryTypeFilter.All)
    val typeFilter = _typeFilter.asStateFlow()

    private val _searchFilter = MutableStateFlow("")

    val filters =  listOf(
        "All" to InventoryTypeFilter.All,
        "Cookies" to InventoryTypeFilter.Cookie,
        "Treasure" to InventoryTypeFilter.Treasure)
    val raritiesList = listOf(
       "Common" to Rarity.Common,
        "Rare" to Rarity.Rare,
        "Epic" to Rarity.Epic,
        "Super Epic" to Rarity.Special,
        "Legendary" to Rarity.Legendary,
        "Ancient" to Rarity.Ancient
    )
    var searchFilter by mutableStateOf("")
        private set

    val cookies = combine(
        cookieFlow(),
        _rarityFilter,
        _typeFilter,
        _searchFilter
    ) { cookies, rarityFilter, typeFilter, searchFilter ->
        when (typeFilter) {
            InventoryTypeFilter.All, InventoryTypeFilter.Cookie  -> {
               val lstFilteredRarity = if (rarityFilter.isNotEmpty()) {
                    cookies.filter { it.cookie.rarity in rarityFilter }
                } else  cookies
                val lstFilteredSearch = if(searchFilter.isNotBlank()) lstFilteredRarity.filter {
                    searchFilter.lowercase() in it.cookie.id.lowercase()
                } else lstFilteredRarity
                lstFilteredSearch.sortedBy { it.cookie.rarity.toComparable() }
            }
            InventoryTypeFilter.Treasure -> emptyList()
        }
    }

    val treasure = combine(
        treasureFlow(),
        _rarityFilter,
        _typeFilter,
        _searchFilter
    ) { treasure, rarityFilter, typeFilter, searchFilter ->
        when (typeFilter) {
            InventoryTypeFilter.All, InventoryTypeFilter.Treasure  -> {
                val lstFilteredRarity = if (rarityFilter.isNotEmpty()) {
                    treasure.filter { it.treasure.rarity in rarityFilter }
                } else  treasure
                val lstFilteredSearch = if(searchFilter.isNotBlank()) lstFilteredRarity.filter {
                    searchFilter.lowercase() in it.treasure.id.lowercase()
                } else lstFilteredRarity
                lstFilteredSearch.sortedBy { it.treasure.rarity.toComparable() }
            }
            InventoryTypeFilter.Cookie -> emptyList()
        }
    }
    fun changeRarityFilter(rarity: Rarity) = viewModelScope.launch {
            _rarityFilter.emit(
                buildSet {
                    if(_rarityFilter.value.contains(rarity)) {
                        _rarityFilter.value.forEach {
                            if (it != rarity) { add(it) }
                        }
                    } else {
                        addAll(_rarityFilter.value)
                        add(rarity)
                    }
                }
            )
        }



    fun changeTypeFilter(typeFilter: InventoryTypeFilter) =
        viewModelScope.launch { _typeFilter.emit(typeFilter) }

    private var searchJob: Job? = null
    fun changeSearchFilter(text: String) = viewModelScope.launch {
        searchFilter = text
        searchJob?.cancel()
        searchJob = launch {
            delay(200)
            _searchFilter.emit(text)
        }
    }
}

sealed interface InventoryTypeFilter {
    object All: InventoryTypeFilter
    object Cookie: InventoryTypeFilter
    object Treasure: InventoryTypeFilter
}