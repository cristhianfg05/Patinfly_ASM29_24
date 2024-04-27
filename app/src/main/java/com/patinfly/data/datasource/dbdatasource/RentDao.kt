package com.patinfly.data.datasource.dbdatasource
import androidx.room.*
import com.patinfly.data.model.dbdatasource.RentEntity
import java.util.UUID

@Dao
interface RentDao {
    @Insert
    fun save(rentEntity: RentEntity)

    @Query("SELECT * FROM RentEntity WHERE uuid = :uuid")
    fun getByUUID(uuid: UUID): RentEntity?

    @Query("SELECT * FROM RentEntity WHERE userUuid = :userUuid")
    fun getByUserUUID(userUuid: UUID): List<RentEntity>

    @Query("SELECT * FROM RentEntity")
    fun getAll(): List<RentEntity>

    @Update
    fun update(rentEntity: RentEntity)

    @Delete
    fun delete(rentEntity: RentEntity)
}