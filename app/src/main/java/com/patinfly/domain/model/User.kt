package com.patinfly.domain.model

import java.util.UUID
import java.util.Date

data class User(
    val uuid: UUID,
    var username: String,
    var email: String,
    var isRenting: Boolean,
    var scooterRented: UUID?,
    val creationDate: Date,
    var numberOfRents: Int,
    var encryptedKey: String
)
