package ru.sanchozgamesstore.android.data.local.database.datasources.user

import androidx.lifecycle.LiveData
import ru.sanchozgamesstore.android.base.BaseDaoDataSource
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.local.database.dao.UserDao
import ru.sanchozgamesstore.android.data.local.database.models.UserRoomModel
import javax.inject.Inject

class UserDaoDataSource @Inject constructor(
    private val userDao: UserDao,
) : BaseDaoDataSource() {
    suspend fun insert(userRoomModel: UserRoomModel): Resource<Unit> = getResultAsync {
        userDao.insert(userRoomModel)
    }

    suspend fun update(userRoomModel: UserRoomModel): Resource<Unit> = getResultAsync {
        userDao.update(userRoomModel)
    }

    suspend fun delete(): Resource<Unit> = getResultAsync {
        userDao.delete()
    }

    suspend fun getUser(): Resource<UserRoomModel?> = getResultAsync {
        userDao.getUser()
    }

    fun getUserLiveData(): LiveData<UserRoomModel?> = getResult {
        userDao.getUserLiveData()
    }
}