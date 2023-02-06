package io.silv.crcsim.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import io.silv.crcsim.feat_gacha.usecases.Pity
import kotlinx.coroutines.flow.Flow

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")

interface UserDataStore {

    val crystalsFlow: Flow<Int>

    val pity: Flow<Pity>

    suspend fun editPity(pity: Pity)

    suspend fun editCrystals(value: Int)

    suspend fun addCrystals(value: Int)
}
