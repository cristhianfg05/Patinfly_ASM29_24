package com.patinfly.data.model

import java.util.Date
import java.util.UUID

data class UserModel(
    val uuid: UUID,
    var username: String,
    var email: String,
    var isRenting: Boolean,
    var scooterRented: UUID?,
    val creationDate: Date,
    var numberOfRents: Int,
    var encryptedKey: String
)