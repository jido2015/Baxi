package com.olajide.capricon.login.data

import android.util.Base64
import android.util.Log
import com.olajide.capricon.Constants
import com.olajide.capricon.Resource
import com.olajide.capricon.login.domain.LoginRepository
import okhttp3.ResponseBody
import org.json.JSONObject
import javax.inject.Inject

class LoginDatasource @Inject constructor (private val api: LoginApiService) : LoginRepository {
    override suspend fun onUserLogin(loginObj: LoginObject): Resource<ResponseBody> {
        return try {
            val creds = String.format("%s:%s", "mobile-client", "priest")
            val auth = "Basic " + Base64.encodeToString(creds.toByteArray(), Base64.DEFAULT)
            val response = api.getLoginAccess(
                auth.trim(), Constants.CONTENT_TYPE, loginObj.username,
                loginObj.password,
                "android"
            )

            val result = response.body()
            if (response.isSuccessful && result != null) {
                Log.d("ErrorResponse", response.toString())
                Resource.Success(result)
            }else {
                val jObjError = JSONObject(response.errorBody()!!.string())

                Log.d("ErrorResponse", jObjError.toString())
                val errorMessage = jObjError.getString("error_description").toString()
                Resource.Failure(errorMessage)
            }
        } catch (e: Exception) {
            Resource.Exception(e.message ?: "An error occurred")
        }
    }
}
