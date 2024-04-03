package com.patinfly.data.repository

import com.patinfly.data.datasource.UserDataSource
import com.patinfly.data.model.UserModel
import com.patinfly.domain.repository.IUserRepository
import java.util.Date
import java.util.UUID

class UserRepository(private val userDataSource: UserDataSource): IUserRepository {

    override fun getUserByUUID(uuid: UUID): UserModel? {
        if(uuid.equals(null))
            return null
        return userDataSource.getUser(uuid)
    }

    override fun getUserByUsername(username: String): UserModel? {
        if(username.isEmpty())
            return null
        return userDataSource.getUser(username)
    }

    override fun login(username: String, password: String): UserModel? {
        val user = getUserByUsername(username)
        if (user != null) {
            if(user.encryptedKey == userDataSource.hashPassword(password)) {
                return user
            }
        }
        return null
    }

    override fun registerUser(newUser: UserModel):Boolean {
        if(getUserByUsername(newUser.username) != null){
            return false
        }
        newUser.encryptedKey = userDataSource.hashPassword(newUser.encryptedKey)
        return userDataSource.saveUser(newUser)
    }

    override fun modifyUser(user:UserModel): Boolean {
        return userDataSource.modifyUser(user.uuid, user.username, user.email, user.encryptedKey)
    }


}