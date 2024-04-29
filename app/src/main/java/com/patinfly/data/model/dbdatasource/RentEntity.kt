package com.patinfly.data.model.dbdatasource

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "RentEntity")
data class RentEntity(
    @PrimaryKey val uuid: UUID,
    val userUuid: UUID,
    val scooterUuid: UUID,
    val startTime: Date,
    val endTime: Date?
)
