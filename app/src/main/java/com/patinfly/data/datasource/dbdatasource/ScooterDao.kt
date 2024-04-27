package com.patinfly.data.datasource.dbdatasource

import androidx.room.*
import com.patinfly.data.model.dbdatasource.ScooterEntity
import java.util.UUID

@Dao
interface ScooterDao {
    @Insert
    fun save(scooterEntity: ScooterEntity)

    @Query("SELECT * FROM ScooterEntity WHERE uuid = :uuid")
    fun getByUUID(uuid: UUID): ScooterEntity?

    @Query("SELECT * FROM ScooterEntity")
    fun getAll(): List<ScooterEntity>

    @Update
    fun update(scooterEntity: ScooterEntity)

    @Delete
    fun delete(scooterEntity: ScooterEntity)
}
