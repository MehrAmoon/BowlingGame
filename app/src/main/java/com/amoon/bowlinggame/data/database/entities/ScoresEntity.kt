package com.amoon.bowlinggame.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores_tbl")
data class ScoresEntity(

    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    @ColumnInfo(name = "user_name")
    val user_name: String,

    @ColumnInfo(name = "score")
    val score: Int,

    @ColumnInfo(name = "rolls")
    val rolls: String
)