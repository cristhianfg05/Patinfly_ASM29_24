package com.patinfly.data.model

import java.util.Date
import java.util.UUID

data class UserModel(
    val uuid: UUID,
    val username: String,
    val email: String,
    val isRenting: Boolean,
    val scooterRented: UUID?,
    val creationDate: Date,
    val numberOfRents: Int,
    val encryptedKey: String
)