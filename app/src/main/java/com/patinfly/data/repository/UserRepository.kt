package com.patinfly.data.repository

import android.util.Log
import com.patinfly.data.datasource.UserDataSource
import com.patinfly.data.model.UserModel
import com.patinfly.domain.model.User
import com.patinfly.domain.repository.IUserRepository
import java.security.MessageDigest
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
            if(user.encryptedKey == hashPassword(password)) {
                return user
            }
        }
        return null
    }

    override fun registerUser(newUser: UserModel):Boolean {
        newUser.encryptedKey = hashPassword(newUser.encryptedKey)
        return userDataSource.saveUser(newUser)
    }

    private fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val result = digest.digest(password.toByteArray(Charsets.UTF_8))
        return bytesToHex(result)
    }

    private fun bytesToHex(hash: ByteArray): String {
        val hexString = StringBuilder(2 * hash.size)
        for (b in hash) {
            val hex = Integer.toHexString(0xff and b.toInt())
            if (hex.length == 1) {
                hexString.append('0')
            }
            hexString.append(hex)
        }
        return hexString.toString()
    }
}