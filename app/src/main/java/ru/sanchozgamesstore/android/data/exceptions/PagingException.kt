package ru.sanchozgamesstore.android.data.exceptions

import ru.sanchozgamesstore.android.data.domain.response.Resource

class PagingException(data: Resource<Any>) :
    RuntimeException("HttpException: msg=${data.errorBody?.error}, httpCode: ${data.errorBody?.httpCode}")