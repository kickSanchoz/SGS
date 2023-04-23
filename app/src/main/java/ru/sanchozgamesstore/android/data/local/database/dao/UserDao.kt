package ru.sanchozgamesstore.android.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.sanchozgamesstore.android.data.local.database.models.UserRoomModel

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userRoomModel: UserRoomModel)

    @Update
    suspend fun update(userRoomModel: UserRoomModel)

    @Query("DELETE FROM user")
    suspend fun delete()

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserRoomModel?

    @Query("SELECT * FROM user LIMIT 1")
    fun getUserLivaData(): LiveData<UserRoomModel?>
}