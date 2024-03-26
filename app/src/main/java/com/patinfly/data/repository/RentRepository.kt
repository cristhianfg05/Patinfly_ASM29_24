package com.patinfly.data.repository

import com.patinfly.data.datasource.RentDataSource
import com.patinfly.data.model.RentModel
import com.patinfly.domain.repository.IRentRepository
import java.util.UUID

class RentRepository(private val rentDataSource: RentDataSource): IRentRepository {

    override fun createRent(rentModel: RentModel) {
        rentDataSource.saveRent(rentModel)
    }

    override fun getRentByUserUUID(userUUID: UUID): List<RentModel> {
        return rentDataSource.getRents().filter { it.userUUID == userUUID }
    }

    override fun deleteRent(rentUUID: UUID) {
        rentDataSource.deleteRent(rentUUID)
    }
}
