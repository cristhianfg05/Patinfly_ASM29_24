package com.patinfly.data.datasource.dbdatasource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.patinfly.data.model.dbdatasource.UserEntity
import java.util.UUID


@Dao
interface UserDao {
    @Insert
    fun save(userEntity: UserEntity)
    @Query("select * from UserEntity where uuid = :uuid")
    fun getUserByUUID(uuid: UUID): UserEntity?
    @Query("select * from UserEntity where email = :email")
    fun getUserByMail(email: String): UserEntity?
    @Query("select * from UserEntity")
    fun getAll(): List<UserEntity>
    @Update
    fun update(userEntity: UserEntity)
    @Delete
    fun delete(userEntity: UserEntity)
}