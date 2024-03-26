package com.patinfly.domain.repository

import com.patinfly.data.model.UserModel
import com.patinfly.domain.model.User
import java.util.UUID

interface IUserRepository {
    fun getUserByUUID(uuid: UUID): UserModel?
    fun login(username: String, password: String): UserModel?
    fun registerUser(newUser: UserModel)
}