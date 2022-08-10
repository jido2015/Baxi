package com.olajide.capricon.login.domain

import com.olajide.capricon.Resource
import com.olajide.capricon.login.data.LoginObject
import com.olajide.capricon.login.data.ResponseContent

interface LoginRepository {
    suspend fun onUserLogin(loginObj: LoginObject): Resource<ResponseContent>
}