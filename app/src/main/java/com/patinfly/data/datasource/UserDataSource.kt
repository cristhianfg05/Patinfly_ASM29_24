package com.patinfly.data.datasource

import android.content.Context
import android.util.Log
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.patinfly.R
import com.patinfly.data.model.UserModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.patinfly.customUtils.UUIDDeserializer
import java.util.UUID

class UserDataSource(private val context: Context) {

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

    fun saveUser(newUser: UserModel) {
        val users = loadUsers().toMutableList()
        if (!users.any { it.uuid == newUser.uuid }) {
            users.add(newUser)
            saveUsersToFile(users)
        } else {
            // Manejar el caso en que el usuario ya existe.
        }
    }


    private fun saveUsersToFile(users: List<UserModel>) {
        //TODO
    }
}