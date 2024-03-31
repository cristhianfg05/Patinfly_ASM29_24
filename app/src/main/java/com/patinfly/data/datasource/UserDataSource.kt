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

    fun getUser(username: String): UserModel? {
        inMemoryUsers.find { it.username == username }?.let { return it }
        initialUsers.find { it.username == username }?.let { return it }
        Log.d("inMemoryUsers", inMemoryUsers.toString())
        return null
    }

    fun saveUser(newUser: UserModel): Boolean {
        /* TODO IMPLEMENTAR SHARED PREFERENCES*/
        return inMemoryUsers.add(newUser)
        /*if (!inMemoryUsers.any { it.uuid == newUser.uuid }) {
            inMemoryUsers.add(newUser)
            saveUsersToFile()
            return true
        }
        return false*/
    }



}