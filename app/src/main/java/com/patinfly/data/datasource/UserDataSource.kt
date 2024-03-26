package com.patinfly.data.datasource

import android.content.Context
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.patinfly.R
import com.patinfly.data.model.UserModel
import com.patinfly.domain.model.User
import java.util.UUID
import com.google.gson.Gson

class UserDataSource(private val context: Context) {

    private fun loadUsers(): List<UserModel> {
        val inputStream = context.resources.openRawResource(R.raw.user)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<User>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    fun getUser(uuid: UUID): UserModel? {
        val users = loadUsers()
        return users.find { it.uuid == uuid }
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