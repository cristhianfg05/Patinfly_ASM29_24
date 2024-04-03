package com.patinfly.data.datasource

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.patinfly.R
import com.patinfly.data.model.UserModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.patinfly.customUtils.UUIDDeserializer
import java.security.MessageDigest
import java.util.UUID

class UserDataSource private constructor(private val context: Context) {

    companion object{

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance : UserDataSource?=null

        fun getInstance(context: Context): UserDataSource=
            instance?: synchronized(this){
                instance ?: UserDataSource(context.applicationContext).also { instance = it }
            }
    }

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(UUID::class.java, UUIDDeserializer())
        .create()

    private val initialUsers by lazy { loadInitialUsers() }

    private val inMemoryUsers = mutableListOf<UserModel>()

    private fun loadInitialUsers(): List<UserModel> {
        val inputStream = context.resources.openRawResource(R.raw.user)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<UserModel>>() {}.type
        return gson.fromJson(jsonString, listType)
    }

    fun getUser(uuid: UUID): UserModel? {
        inMemoryUsers.find { it.uuid == uuid }?.let { return it }
        initialUsers.find { it.uuid == uuid }?.let { return it }
        Log.d("inMemoryUsers", inMemoryUsers.toString())
        return null
    }

    fun getUser(username: String): UserModel? {
        inMemoryUsers.find { it.username == username }?.let { return it }
        initialUsers.find { it.username == username }?.let { return it }
        Log.d("inMemoryUsers", inMemoryUsers.toString())
        return null
    }

    fun saveUser(newUser: UserModel): Boolean {
        return inMemoryUsers.add(newUser)

    }

    fun modifyUser(uuid: UUID,username: String, email: String, password: String): Boolean{
        val userIndex = inMemoryUsers.indexOfFirst { it.uuid == uuid }
        if (userIndex != -1) {
            val user = inMemoryUsers[userIndex]
            user.email = email
            user.username = username
            user.encryptedKey = hashPassword(password)
            return true
        }
        return false
    }

    fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val result = digest.digest(password.toByteArray(Charsets.UTF_8))
        return bytesToHex(result)
    }

    fun bytesToHex(hash: ByteArray): String {
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