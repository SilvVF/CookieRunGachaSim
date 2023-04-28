package io.silv.crcsim.feat_cookie_gacha.domain

import io.silv.crcsim.data.datastore.UserDataStore
import io.silv.crcsim.feat_cookie_gacha.Pity
import kotlinx.coroutines.flow.Flow

class UserDataRepo(
   userDataStore: UserDataStore
) {

   val pityFlow: Flow<Pity> = userDataStore.pity

   val crystalsFlow: Flow<Int> = userDataStore.crystalsFlow
}
