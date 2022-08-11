package com.olajide.capricon.login.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.olajide.capricon.base.NetworkResult
import com.olajide.capricon.base.Resource
import com.olajide.capricon.base.launchIo
import com.olajide.capricon.login.data.LoginObject
import com.olajide.capricon.login.data.ResponseContent
import com.olajide.capricon.login.domain.usecase.interaction.LoginInteraction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val interactor: LoginInteraction): ViewModel(){

    //StateFow
    private val _state = MutableSharedFlow<NetworkResult<ResponseContent>>()
    val state = _state.asSharedFlow()


    fun login(obj:LoginObject){
            launchIo {
                _state.emit(NetworkResult.Loading())
                when (val response = interactor.provideLoginUseCase().invoke(obj)) {
                    is Resource.Failure -> {
                        _state.emit(NetworkResult.Failure(response.message.toString()))
                    }
                    is Resource.Success -> {
                        _state.emit(NetworkResult.Success(response.data!!))
                    }
                    is Resource.Exception -> Log.d("LogException", response.message.toString())
                }
            }
        }
}