package io.silv.crcsim.feat_cookie_gacha.domain

import io.silv.crcsim.data.room.dao.PullHistDao
import io.silv.crcsim.data.room.entity.PullHistEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun interface FetchHistoryUseCase : () -> Flow<List<List<Pair<String, Int>>>>

fun interface FetchArtifactHistoryUseCase : () -> Flow<List<List<Pair<String, Int>>>>

fun interface FetchCookieHistoryUseCase : () -> Flow<List<List<Pair<String, Int>>>>

fun Flow<List<PullHistEntity>>.mapToPairs() = this.map { historyList ->
    historyList.map { pull ->
        pull.items.zip(pull.amounts)
    }
}

fun fetchHistoryUseCaseImpl(
    histDao: PullHistDao
):  Flow<List<List<Pair<String, Int>>>> {
    return histDao.getHistory().mapToPairs()
}

fun fetchCookieHistoryUseCaseImpl(
    histDao: PullHistDao
):  Flow<List<List<Pair<String, Int>>>>{
    return histDao.getCookieHistory().mapToPairs()
}

fun fetchArtifactHistoryUseCaseImpl(
    histDao: PullHistDao
):  Flow<List<List<Pair<String, Int>>>> {
    return histDao.getArtifactHistory().mapToPairs()
}




