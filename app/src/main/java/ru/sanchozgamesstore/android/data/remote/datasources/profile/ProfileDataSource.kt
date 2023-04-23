package ru.sanchozgamesstore.android.data.remote.datasources.profile

import ru.sanchozgamesstore.android.base.BaseDataSource
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.remote.models.user.UserApiModel
import ru.sanchozgamesstore.android.data.remote.services.ProfileService
import javax.inject.Inject

class ProfileDataSource @Inject constructor(
    private val profileService: ProfileService,
) : BaseDataSource() {
    suspend fun fetchProfile(): Resource<UserApiModel> = getResult{
        profileService.fetchUser()
    }
}