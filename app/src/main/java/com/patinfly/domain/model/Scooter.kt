package com.patinfly.domain.model

import java.util.UUID
import java.util.Date

data class Scooter(
    val uuid: UUID,
    val model: String,
    val serialNumber: String,
    var longitude: Double,
    var latitude: Double,
    var vacant: Boolean,
    var batteryLevel: Double,
    val batteryPartNumber: String,
    var lastMaintenance: Date? = null
)
