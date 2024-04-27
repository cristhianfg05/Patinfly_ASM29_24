package com.patinfly.data.datasource

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.patinfly.R
import com.patinfly.customUtils.UUIDDeserializer
import com.patinfly.data.model.RentModel
import com.patinfly.data.model.UserModel
import java.util.UUID

class RentDataSource private constructor(private val context: Context) {

    companion object{

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance : RentDataSource?=null

        fun getInstance(context: Context): RentDataSource=
            instance?: synchronized(this){
                instance ?: RentDataSource(context.applicationContext).also { instance = it }
            }
    }

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(UUID::class.java, UUIDDeserializer())
        .create()

    private val initialRents by lazy { loadRents() }

    private val inMemoryRents = mutableListOf<RentModel>()

    private fun loadRents(): List<RentModel> {
        val inputStream = context.resources.openRawResource(R.raw.rent)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<RentModel>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    fun getRents(): List<RentModel> {
        return loadRents()
    }

    fun saveRent(newRent: RentModel):Boolean {
        return inMemoryRents.add(newRent)
    }

    fun deleteRent(rentUUID: UUID) {
        val rents = loadRents().filterNot { it.uuid == rentUUID }
        saveRentsToFile(rents)
    }

    private fun saveRentsToFile(rents: List<RentModel>) {
        //TODO
    }
}
