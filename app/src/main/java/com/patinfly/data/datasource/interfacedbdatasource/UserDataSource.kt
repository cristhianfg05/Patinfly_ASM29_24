package com.patinfly.data.datasource.interfacedbdatasource

import com.patinfly.data.model.dbdatasource.UserEntity
import java.util.UUID

interface UserDataSource {
    fun getUser(uuid: UUID): UserEntity?
    fun getUserByEmail(email: String): UserEntity?
    fun saveUser(user: UserEntity)
    fun updateUser(user: UserEntity)
    fun deleteUser(user: UserEntity)
}