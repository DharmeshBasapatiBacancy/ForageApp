package com.kudos.forageapp.db.dao

import androidx.room.*
import com.kudos.forageapp.db.entities.Forage
import kotlinx.coroutines.flow.Flow

@Dao
interface ForageDao {

    @Insert
    suspend fun addNewForage(forage: Forage)

    @Update
    suspend fun updateForage(forage: Forage)

    @Delete
    suspend fun deleteForage(forage: Forage)

    @Query("SELECT * FROM forage")
    fun getAllForages(): Flow<List<Forage>>

    @Query("SELECT * FROM forage WHERE forageId = :forageId")
    fun getForageByID(forageId: Int): Flow<Forage>

}