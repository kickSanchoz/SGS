package ru.sanchozgamesstore.android.data.domain.models.user

import ru.sanchozgamesstore.android.data.domain.models.ui.StringField
import ru.sanchozgamesstore.android.data.remote.models.authorization.AuthorizationApiModel

/**
 * Модель пользователя при авторизации
 */
class UserAuthorizationModel {
    val email = StringField(true)
    val password = StringField(true)

    /**
     * Поля для авторизации
     * */
    private val authorizationFields = listOf(
        email,
        password
    )

    /**
     * Заполнены ли все поля авторизации
     * */
    fun isAuthorizationFieldsIsFilled(): Boolean {
        return authorizationFields.onEach {
            it.validateField()
        }.all {
            !it.isError
        }
    }
}

fun UserAuthorizationModel.toAuthorizationApiModel(): AuthorizationApiModel {
    return AuthorizationApiModel(
        email = email.data.value ?: "",
        password = password.data.value ?: "",
    )
}