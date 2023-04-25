package ru.sanchozgamesstore.android.base

import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.liveData
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.domain.response.Resource.Status.ERROR
import ru.sanchozgamesstore.android.data.domain.response.Resource.Status.LOADING
import ru.sanchozgamesstore.android.data.domain.response.Resource.Status.NETWORK_ERROR
import ru.sanchozgamesstore.android.data.domain.response.Resource.Status.SUCCESS
import ru.sanchozgamesstore.android.data.exceptions.PagingException
import ru.sanchozgamesstore.android.data.remote.models.PaginationResponse

abstract class BasePagingSource<Domain : Any, Remote : Any, PagingResponse : PaginationResponse<Remote>> :
    PagingSource<Int, Domain>() {

    override fun getRefreshKey(state: PagingState<Int, Domain>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    abstract suspend fun onLoad(pageNumber: Int, pageSize: Int): Resource<PagingResponse>

    abstract fun convert(response: PagingResponse): List<Domain>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Domain> {
        val pageNumber = params.key ?: 1
        val pageSize = params.loadSize.coerceAtMost(MAX_PAGE_SIZE)

        val res = onLoad(pageNumber, pageSize)
        if (res.data?.count == null) {
            throw IllegalStateException("Paging data from server not found\n{${res.errorBody}}")
        }

        val data = when (res.status) {
            SUCCESS -> {
                val next = res.data.next?.toUri()?.getQueryParameter("page")?.toInt()

                /*
                * Если предыдущей страницы нет:
                *   вернуть null
                * Предыдущая страница есть:
                *   Если в url ссылке предыдущей странице есть параметр "page":
                *       вернуть значение по этому параметру
                *   Иначе
                *       (т.к. бэк не отдаёт данный параметр, если предыдущая страница - первая)
                *       вернуть 1
                * */
                val previous = res.data.previous.let {
                    if (it.isNullOrBlank()) {
                        null
                    } else {
                        val parameter = it.toUri().getQueryParameter("page")?.toInt()
                        parameter ?: 1
                    }
                }

                LoadResult.Page(
                    data = convert(res.data),
                    prevKey = previous,
                    nextKey = next,
                )
            }

            LOADING -> {
                throw IllegalStateException("Status LOADING in Resource")
            }

            ERROR, NETWORK_ERROR -> {
                LoadResult.Error(PagingException(res))
            }
        }

        Log.d("Source", "Load result: $data")
        return data
    }

    open fun getPageConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 5,
            enablePlaceholders = true
        )
    }

    open fun getPagerLiveData(): LiveData<PagingData<Domain>> {
        return Pager(
            config = getPageConfig(),
            pagingSourceFactory = {
                this
            }
        ).liveData
    }

    companion object {
        const val MAX_PAGE_SIZE = 5
    }

}