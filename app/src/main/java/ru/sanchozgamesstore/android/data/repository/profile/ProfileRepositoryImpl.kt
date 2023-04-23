package ru.sanchozgamesstore.android.data.repository.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ru.sanchozgamesstore.android.data.domain.models.user.UserModel
import ru.sanchozgamesstore.android.data.domain.models.user.toModel
import ru.sanchozgamesstore.android.data.domain.models.user.toRoom
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.local.database.datasources.user.UserDaoDataSource
import ru.sanchozgamesstore.android.data.local.datastore.ApiKeyDataStore
import ru.sanchozgamesstore.android.data.remote.datasources.profile.ProfileDataSource
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource,
    private val apiKeyDataStore: ApiKeyDataStore,
    private val userDaoDataSource: UserDaoDataSource,
) : ProfileRepository {
    override suspend fun fetchProfile(): Resource<UserModel> = withContext(IO) {
        val response = profileDataSource.fetchProfile()

        if (response.dataLoaded && response.data?.api_key != null) {
            apiKeyDataStore.setApiKey(response.data.api_key)

            saveProfileLocal(response.data.toModel())
        }

        return@withContext response.map {
            it?.toModel()
        }
    }

    override suspend fun getProfile(): Resource<UserModel> = withContext(IO) {
        fetchProfile()
        val res = userDaoDataSource.getUser()

        return@withContext res.map {
            it?.toModel()
        }
    }

    override suspend fun getProfileLiveData(): LiveData<Resource<UserModel>> = liveData(IO) {
        val res = userDaoDataSource.getUserLivaData()

        /*
        * Если данных в БД ещё нет (например, первый вход в приложение):
        *   Сначала запросить и сохранить данные с бэка
        *   Затем установить источник данных
        * Иначе:
        *   Сначала установить источник данных
        *   Затем запросить и сохранить новые данные с бэка
        * */
        if (res.value == null) {
            fetchProfile()
            emitSource(res.map {
                Resource.success(it!!.toModel())
            })
        } else {
            emitSource(
                userDaoDataSource.getUserLivaData().map {
                    Resource.success(it!!.toModel())
                }
            )
            fetchProfile()
        }
    }


    override suspend fun saveProfileLocal(userModel: UserModel): Unit = withContext(IO) {
        userDaoDataSource.insert(userModel.toRoom())
    }

    override suspend fun clearProfileLocalData(): Resource<Unit> = withContext(IO) {
        userDaoDataSource.delete()
    }
}