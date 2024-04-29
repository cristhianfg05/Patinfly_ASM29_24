package com.patinfly.data.datasource.localdatasource

import com.patinfly.data.datasource.dbdatasource.ScooterDao
import com.patinfly.data.datasource.interfacedbdatasource.ScooterDataSource
import com.patinfly.data.model.dbdatasource.ScooterEntity
import java.util.UUID

class RoomScooterDataSource(private val scooterDao: ScooterDao) : ScooterDataSource {
    override fun getScooter(uuid: UUID): ScooterEntity? = scooterDao.getByUUID(uuid)
    override fun getAllScooters(): List<ScooterEntity> = scooterDao.getAll()
    override fun saveScooter(scooter: ScooterEntity) = scooterDao.save(scooter)
    override fun updateScooter(scooter: ScooterEntity) = scooterDao.update(scooter)
    override fun deleteScooter(scooter: ScooterEntity) = scooterDao.delete(scooter)
}