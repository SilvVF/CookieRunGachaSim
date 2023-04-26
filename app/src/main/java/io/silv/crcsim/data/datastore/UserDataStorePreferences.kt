package io.silv.crcsim.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import io.silv.crcsim.feat_cookie_gacha.Pity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map


class UserDataStorePreferences(
    private val dataStore: DataStore<Preferences>
): UserDataStore {

    private val crystalsKey = intPreferencesKey("crystals_key")
    private val anyPityKey = intPreferencesKey("any_key")
    private val epicPityKey = intPreferencesKey("epic_key")
    private val otherPityKey = intPreferencesKey("other_key")

    override val crystalsFlow: Flow<Int> = dataStore.data
        .map { preferences ->
            runCatching {
                preferences[crystalsKey] ?: 0
            }
                .getOrDefault(0)
        }

    override suspend fun editCrystals(value: Int) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[crystalsKey] = value
        }
    }

    override suspend fun addCrystals(value: Int) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[crystalsKey] = value + (crystalsFlow.firstOrNull() ?: 0)
        }
    }

    override val pity: Flow<Pity> = dataStore.data
        .map { preferences ->
            Pity (
                any = preferences[anyPityKey] ?: 0,
                epic = preferences[epicPityKey] ?: 0,
                other = preferences[otherPityKey] ?: 0,
            )
        }


    override suspend fun editPity(pity: Pity) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[anyPityKey] = pity.any
            mutablePreferences[epicPityKey] = pity.epic
            mutablePreferences[otherPityKey] = pity.other
        }
    }
}