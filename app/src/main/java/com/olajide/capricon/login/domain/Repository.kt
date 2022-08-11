package com.olajide.capricon.login.domain

import com.olajide.capricon.base.Resource
import com.olajide.capricon.login.data.LoginObject
import com.olajide.capricon.login.data.ResponseContent

interface Repository {
    suspend fun onUserLogin(loginObj: LoginObject): Resource<ResponseContent>
}