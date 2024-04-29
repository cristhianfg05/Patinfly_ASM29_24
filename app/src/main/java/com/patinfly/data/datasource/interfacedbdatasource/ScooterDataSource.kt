package com.patinfly.data.datasource.interfacedbdatasource

import com.patinfly.data.model.dbdatasource.ScooterEntity
import java.util.UUID

interface ScooterDataSource {
    fun getScooter(uuid: UUID): ScooterEntity?
    fun getAllScooters(): List<ScooterEntity>
    fun saveScooter(scooter: ScooterEntity)
    fun updateScooter(scooter: ScooterEntity)
    fun deleteScooter(scooter: ScooterEntity)
}