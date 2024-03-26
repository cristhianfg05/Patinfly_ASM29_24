package com.patinfly.data.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.patinfly.R
import com.patinfly.data.model.RentModel
import java.util.UUID

class RentDataSource(private val context: Context) {

    private fun loadRents(): List<RentModel> {
        val inputStream = context.resources.openRawResource(R.raw.rent)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<RentModel>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    fun getRents(): List<RentModel> {
        return loadRents()
    }

    fun saveRent(newRent: RentModel) {
        val rents = loadRents().toMutableList()
        rents.add(newRent)
        saveRentsToFile(rents)
    }

    fun deleteRent(rentUUID: UUID) {
        val rents = loadRents().filterNot { it.uuid == rentUUID }
        saveRentsToFile(rents)
    }

    private fun saveRentsToFile(rents: List<RentModel>) {
        //TODO
    }
}
