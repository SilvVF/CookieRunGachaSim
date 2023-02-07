package io.silv.crcsim.feat_gacha.usecases

import io.silv.crcsim.data.datastore.UserDataStore
import io.silv.crcsim.feat_gacha.Pity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn

class UserDataRepo(
    private val userDataStore: UserDataStore
) {

   val pityFlow: Flow<Pity> = userDataStore.pity

   val crystalsFlow: Flow<Int> = userDataStore.crystalsFlow
}