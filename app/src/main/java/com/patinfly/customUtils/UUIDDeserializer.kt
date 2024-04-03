package com.patinfly.customUtils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.util.UUID

class UUIDDeserializer : JsonDeserializer<UUID?> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): UUID? {
        try {
            if (json == null || json.asString.isEmpty()) {
                return null
            }
            return UUID.fromString(json.asString)
        } catch (e: IllegalArgumentException) {
            return null
        }
    }
}