package com.patinfly.data.repository

import com.patinfly.data.datasource.ScooterDataSource
import com.patinfly.data.model.ScooterModel
import com.patinfly.domain.repository.IScooterRepository
import java.util.UUID

class ScooterRepository(private val scooterDataSource: ScooterDataSource): IScooterRepository {

    override fun getAllScooters(): List<ScooterModel> {
        return scooterDataSource.getScooters()
    }

    override fun getScooterByUUID(uuid: UUID): ScooterModel? {
        return scooterDataSource.getScooter(uuid)
    }

    override fun updateScooter(scooterModel: ScooterModel) {
        scooterDataSource.saveScooter(scooterModel)
    }

    override fun addNewScooter(scooterModel: ScooterModel) {
        scooterDataSource.saveScooter(scooterModel)
    }


}
