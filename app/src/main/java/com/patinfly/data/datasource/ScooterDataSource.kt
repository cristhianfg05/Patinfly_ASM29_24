package com.patinfly.data.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.patinfly.R
import com.patinfly.data.model.ScooterModel
import java.util.UUID

class ScooterDataSource(private val context: Context) {

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
        val scooters = loadScooters()
        return scooters.find { it.uuid == uuid }
    }

    fun saveScooter(updatedScooter: ScooterModel) {
        //TODO
    }
}
