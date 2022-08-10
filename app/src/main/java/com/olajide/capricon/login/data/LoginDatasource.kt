package com.olajide.capricon.login.data

import android.util.Log
import com.olajide.capricon.Resource
import com.olajide.capricon.login.domain.LoginRepository
import org.json.JSONObject
import javax.inject.Inject

class LoginDatasource @Inject constructor (private val api: LoginApiService) : LoginRepository {
    override suspend fun onUserLogin(loginObj: LoginObject): Resource<ResponseContent> {
        return try {
            val response = api.getLoginAccess(loginObj)

            val result = response.body()
            if (response.isSuccessful && result != null) {
                Log.d("ErrorResponse", response.toString())
                Resource.Success(result)
            }else {
                val errorMessage =  result!!.resp_message
                Resource.Failure(errorMessage)
            }
        } catch (e: Exception) {
            Resource.Exception(e.message ?: "An error occurred")
        }
    }
}
