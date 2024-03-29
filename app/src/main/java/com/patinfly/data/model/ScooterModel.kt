package com.patinfly.data.model

import java.util.Date
import java.util.UUID

data class ScooterModel(
    val uuid: UUID,
    val model: String,
    val serialNumber: String,
    val longitude: Double,
    val latitude: Double,
    val vacant: Boolean,
    val batteryLevel: Double,
    val batteryPartNumber: String,
    val lastMaintenance: Date? = null
)