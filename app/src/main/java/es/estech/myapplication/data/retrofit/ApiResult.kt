package es.estech.myapplication.data.retrofit

import es.estech.myapplication.data.models.breeds.Breed

sealed class ApiResult <out T> (
    val data : T? = null,
    val errorData: String? = null
) {


    class Success<T : Any>(data : T) : ApiResult<T>(data = data)
    class ApiError (message: String) : ApiResult<Nothing>(errorData = message)

    class Loading : ApiResult<Nothing>()
}