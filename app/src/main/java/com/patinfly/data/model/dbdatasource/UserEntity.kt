package com.patinfly.data.model.dbdatasource

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "UserEntity")
data class UserEntity(
    @PrimaryKey val uuid: UUID,
    var username: String,
    var email: String,
    var isRenting: Boolean,
    var scooterRented: UUID?,
    val creationDate: Date,
    var numberOfRents: Int,
    var encryptedKey: String

)
