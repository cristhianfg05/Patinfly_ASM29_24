package com.patinfly.domain.model

import java.util.UUID
import java.util.Date

data class User(
    val uuid: UUID,
    val username: String,
    val email: String,
    val isRenting: Boolean,
    val scooterRented: UUID,
    val creationDate: Date,
    val numberOfRents: Int,
    val encryptedKey: String
)
