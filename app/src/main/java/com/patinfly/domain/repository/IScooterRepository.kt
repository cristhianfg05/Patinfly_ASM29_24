package com.patinfly.domain.repository

import com.patinfly.data.model.ScooterModel

import java.util.UUID

interface IScooterRepository {
    fun getAllScooters(): List<ScooterModel>
    fun getScooterByUUID(uuid: UUID): ScooterModel?
    fun updateScooter(scooterModel: ScooterModel)

    fun addNewScooter(scooterModel: ScooterModel)
}
