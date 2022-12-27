package com.kudos.forageapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Forage(
    @PrimaryKey(autoGenerate = true)
    val forageId: Int = 0,
    val forageName: String,
    val forageLocation: String,
    val currentlyInSeason: Boolean = false,
    val forageNotes: String = ""
) : java.io.Serializable
