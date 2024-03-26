package com.patinfly.data.repository

import com.patinfly.data.datasource.UserDataSource
import com.patinfly.data.model.UserModel
import com.patinfly.domain.model.User
import com.patinfly.domain.repository.IUserRepository
import java.util.UUID

class UserRepository(private val userDataSource: UserDataSource): IUserRepository {

    override fun getUserByUUID(uuid: UUID): UserModel? {
        return userDataSource.getUser(uuid)
    }

    override fun login(username: String, password: String): UserModel? {
        return null
    }

    override fun registerUser(newUser: UserModel) {
        userDataSource.saveUser(newUser)
    }
}