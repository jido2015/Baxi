package com.olajide.capricon.login.data

import android.util.Log
import com.olajide.capricon.base.Resource
import com.olajide.capricon.login.domain.Repository
import javax.inject.Inject

class Datasource @Inject constructor (private val api: ApiService) : Repository {
    override suspend fun onUserLogin(loginObj: LoginObject): Resource<ResponseContent> {
        return try {
            val response = api.getLoginAccess(loginObj)

            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            }else {
                Log.d("ErrorResponse", response.toString())
                val errorMessage =  result!!.resp_message
                Resource.Failure(errorMessage)
            }
        } catch (e: Exception) {
            Resource.Exception(e.message ?: "An error occurred")
        }
    }
}
