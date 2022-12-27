package com.kudos.forageapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kudos.forageapp.db.dao.ForageDao
import com.kudos.forageapp.db.entities.Forage

@Database(entities = [Forage::class], version = 1, exportSchema = false)
abstract class ForageDatabase : RoomDatabase() {

    abstract fun forageDao(): ForageDao
}