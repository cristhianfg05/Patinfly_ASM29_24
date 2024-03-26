package com.patinfly.domain.model

import java.util.UUID
import java.util.Date

data class Rent(
    val uuid: UUID,
    val scooterUUID: UUID,
    val userUUID: UUID,
    var startDate: Date,
    var stopDate: Date
)
