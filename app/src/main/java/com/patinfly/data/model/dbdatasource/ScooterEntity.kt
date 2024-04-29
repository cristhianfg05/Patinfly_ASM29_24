package com.patinfly.data.model.dbdatasource

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "ScooterEntity")
data class ScooterEntity(
    @PrimaryKey val uuid:UUID,
    val model: String,
    val serialNumber: String,
    val longitude: Double,
    val latitude: Double,
    val vacant: Boolean,
    val batteryLevel: Double,
    val batteryPartNumber: String,
    val lastMaintenance: Date? = null
)
