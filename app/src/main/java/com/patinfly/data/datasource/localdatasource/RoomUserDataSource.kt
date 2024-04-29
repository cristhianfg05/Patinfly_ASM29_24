package com.patinfly.data.datasource.localdatasource

import com.patinfly.data.datasource.dbdatasource.UserDao
import com.patinfly.data.datasource.interfacedbdatasource.UserDataSource
import com.patinfly.data.model.dbdatasource.UserEntity
import java.util.UUID

class RoomUserDataSource(private val userDao: UserDao) :  UserDataSource{
    override fun getUser(uuid: UUID): UserEntity? = userDao.getUserByUUID(uuid)
    override fun getUserByEmail(email: String): UserEntity? = userDao.getUserByMail(email)
    override fun saveUser(user: UserEntity) = userDao.save(user)
    override fun updateUser(user: UserEntity) = userDao.update(user)
    override fun deleteUser(user: UserEntity) = userDao.delete(user)
}