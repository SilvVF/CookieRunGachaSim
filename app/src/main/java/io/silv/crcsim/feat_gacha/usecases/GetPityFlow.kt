package io.silv.crcsim.feat_gacha.usecases

import io.silv.crcsim.data.datastore.UserDataStore
import kotlinx.coroutines.flow.Flow

class GetPityFlow(
    private val userDataStore: UserDataStore
) {

    suspend operator fun invoke(): Flow<Pity> = userDataStore.pity
}