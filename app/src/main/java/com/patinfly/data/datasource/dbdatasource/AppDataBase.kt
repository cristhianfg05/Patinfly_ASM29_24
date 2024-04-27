package com.patinfly.data.datasource.dbdatasource
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.patinfly.data.model.dbdatasource.UserEntity


@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDataSource(): UserDao
    //abstract fun scooterDataSource(): ScooterDao
//abstract fun rentDataSource(): RentDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "patinfly_24_database"
                ).build()
                INSTANCE = instance
// return instance
                instance
            }
        }
    }
}