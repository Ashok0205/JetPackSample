package com.example.jetpacksamplev1.network.utils

import com.example.jetpacksamplev1.util.SOMETHING_WENT_WRONG

sealed class ApiResponse <T>(
    val data: T? = null,
    val errorDescription: String = SOMETHING_WENT_WRONG,
) {


    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is DataError -> "Error[exception=$errorDescription]"
        }
    }

}

class Success<T>(data: T) : ApiResponse<T>(data)
class DataError<T>(errorDescription: String) : ApiResponse<T>(null, errorDescription)