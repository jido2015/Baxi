package com.olajide.capricon.login.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.olajide.capricon.DispatchProvider
import com.olajide.capricon.NetworkResult
import com.olajide.capricon.Resource
import com.olajide.capricon.launchIo
import com.olajide.capricon.login.data.LoginObject
import com.olajide.capricon.login.data.ResponseContent
import com.olajide.capricon.login.domain.usecase.interactor.LoginInteraction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import okhttp3.ResponseBody
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
                Log.d("ProductStatusAPI", "Emitted")
                when (val response = interactor.provideLoginUseCase().invoke(obj)) {
                    is Resource.Failure -> {
                        _state.emit(NetworkResult.Failure(response.message.toString()))
                    }
                    is Resource.Success -> {
                        _state.emit(NetworkResult.Success(response.data!!))
                    }
                    is Resource.Exception -> Log.d("ProductStatusAPI", response.message.toString())
                }
            }
        }

}