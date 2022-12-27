package com.kudos.forageapp.repository

import com.kudos.forageapp.db.dao.ForageDao
import com.kudos.forageapp.db.entities.Forage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ForageRepository @Inject constructor(private val forageDao: ForageDao) {

    suspend fun addNewForage(forage: Forage) {
        forageDao.addNewForage(forage)
    }

    suspend fun updateForage(forage: Forage) {
        forageDao.updateForage(forage)
    }

    suspend fun deleteForage(forage: Forage) {
        forageDao.deleteForage(forage)
    }

    fun getAllForage(): Flow<List<Forage>> {
        return forageDao.getAllForages()
    }

    fun getForageByID(forageId: Int): Flow<Forage> {
        return forageDao.getForageByID(forageId)
    }

}