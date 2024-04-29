package com.patinfly.data.datasource.localdatasource

import com.patinfly.data.datasource.dbdatasource.RentDao
import com.patinfly.data.datasource.interfacedbdatasource.RentDataSource
import com.patinfly.data.model.dbdatasource.RentEntity
import java.util.UUID

class RoomRentDataSource(private val rentDao: RentDao) : RentDataSource {
    override fun getRent(uuid: UUID): RentEntity? = rentDao.getByUUID(uuid)
    override fun getAllRents(): List<RentEntity> = rentDao.getAll()
    override fun saveRent(rent: RentEntity) = rentDao.save(rent)
    override fun updateRent(rent: RentEntity) = rentDao.update(rent)
    override fun deleteRent(rent: RentEntity) = rentDao.delete(rent)
}