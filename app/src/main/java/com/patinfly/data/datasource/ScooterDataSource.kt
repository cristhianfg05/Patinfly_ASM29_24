package com.patinfly.data.datasource

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.patinfly.R
import com.patinfly.customUtils.UUIDDeserializer
import com.patinfly.data.model.ScooterModel
import com.patinfly.data.model.UserModel
import java.util.UUID

class ScooterDataSource private constructor(private val context: Context) {

    companion object{

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance : ScooterDataSource?=null

        fun getInstance(context: Context): ScooterDataSource=
            instance?: synchronized(this){
                instance ?: ScooterDataSource(context.applicationContext).also { instance = it }
            }
    }

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(UUID::class.java, UUIDDeserializer())
        .create()

    private val initialScooters by lazy { loadScooters() }

    private val inMemoryScooters = mutableListOf<ScooterModel>()

    private fun loadScooters(): List<ScooterModel> {
        val inputStream = context.resources.openRawResource(R.raw.scooter)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<ScooterModel>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    fun getScooters(): List<ScooterModel> {
        return loadScooters()
    }

    fun getScooter(uuid: UUID): ScooterModel? {
        inMemoryScooters.find { it.uuid == uuid }?.let { return it }
        initialScooters.find { it.uuid == uuid }?.let { return it }
        return null
    }

    fun saveScooter(updatedScooter: ScooterModel):Boolean {
        return inMemoryScooters.add(updatedScooter)
    }
}
