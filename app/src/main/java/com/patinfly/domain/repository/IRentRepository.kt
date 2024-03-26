package com.patinfly.domain.repository

import com.patinfly.data.model.RentModel

import java.util.UUID

interface IRentRepository {
    fun createRent(rentModel: RentModel)
    fun getRentByUserUUID(userUUID: UUID): List<RentModel>
    fun deleteRent(rentUUID: UUID)
}
