package ru.sanchozgamesstore.android.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.sanchozgamesstore.android.data.local.database.dao.UserDao
import ru.sanchozgamesstore.android.data.local.database.models.UserRoomModel

@Database(
    version = 1,
    entities = [
        UserRoomModel::class,
    ]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        private const val DATABASE_NAME = "sgs_bd"

        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}