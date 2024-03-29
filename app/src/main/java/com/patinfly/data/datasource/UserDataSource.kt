package com.patinfly.data.datasource

import android.content.Context
import android.util.Log
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.patinfly.R
import com.patinfly.data.model.UserModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.patinfly.customUtils.UUIDDeserializer
import java.io.FileOutputStream
import java.util.UUID

class UserDataSource(private val context: Context) {

    private val fileName = "users.json"
    private fun loadUsers(): List<UserModel> {
        val gson: Gson = GsonBuilder()
            .registerTypeAdapter(UUID::class.java, UUIDDeserializer())
            .create()

        val inputStream = context.resources.openRawResource(R.raw.user)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        val listType = object : TypeToken<List<UserModel>>() {}.type
        return gson.fromJson(jsonString, listType)
    }

    fun getUser(username: String): UserModel? {
        val users = loadUsers()
        return users.find { it.username == username }
    }

    fun saveUser(newUser: UserModel): Boolean {
        val users = loadUsers().toMutableList()
        if (!users.any { it.uuid == newUser.uuid }) {
            users.add(newUser)
            saveUsersToFile(users)
            Log.d("flag", "user nuevo")
            return true
        } else {
            return false
        }
    }


    fun saveUsersToFile(users: List<UserModel>) {
        val gson = Gson()
        val jsonString = gson.toJson(users)

        FileOutputStream(context.getFileStreamPath(fileName)).use { outputStream ->
            outputStream.write(jsonString.toByteArray())
        }
    }
}