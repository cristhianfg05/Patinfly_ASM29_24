package com.patinfly.data.datasource.interfacedbdatasource

import com.patinfly.data.model.dbdatasource.RentEntity
import java.util.UUID

interface RentDataSource {
    fun getRent(uuid: UUID): RentEntity?
    fun getAllRents(): List<RentEntity>
    fun saveRent(rent: RentEntity)
    fun updateRent(rent: RentEntity)
    fun deleteRent(rent: RentEntity)
}