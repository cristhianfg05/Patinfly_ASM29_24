package com.patinfly.data.repository

import com.patinfly.data.datasource.UserDataSource
import com.patinfly.data.model.UserModel
import com.patinfly.domain.model.User
import com.patinfly.domain.repository.IUserRepository
import java.util.UUID

class UserRepository(private val userDataSource: UserDataSource): IUserRepository {

    override fun getUserByUsername(username: String): UserModel? {
        if(username.isEmpty())
            return null
        return userDataSource.getUser(username)
    }

    override fun login(username: String, password: String): UserModel? {
        val user = getUserByUsername(username)
        if (user != null) {
            if(user.encryptedKey == password)
                return user
        }
        return null
    }

    override fun registerUser(newUser: UserModel) {

        userDataSource.saveUser(newUser)
    }
}