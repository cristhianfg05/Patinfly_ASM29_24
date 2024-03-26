package com.patinfly.data.model

import java.util.Date
import java.util.UUID

data class RentModel(
    val uuid: UUID,
    val scooterUUID: UUID,
    val userUUID: UUID,
    var startDate: Date,
    var stopDate: Date
)