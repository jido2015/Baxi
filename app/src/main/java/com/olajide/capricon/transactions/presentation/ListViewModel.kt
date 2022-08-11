package com.olajide.capricon.transactions.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.olajide.capricon.base.NetworkResult
import com.olajide.capricon.base.Resource
import com.olajide.capricon.base.launchIo
import com.olajide.capricon.transactions.data.model.Transactions
import com.olajide.capricon.transactions.domain.usecase.interaction.TrnxInteraction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val interactor: TrnxInteraction): ViewModel() {

    //StateFow
    private val _state = MutableSharedFlow<NetworkResult<Transactions>>()
    val state = _state.asSharedFlow()

    fun getTrnxLsst(page: String){
        launchIo {
            _state.emit(NetworkResult.Loading())
            when (val response = interactor.provideTransactionUseCase().invoke(page)) {
                is Resource.Failure -> {
                    _state.emit(NetworkResult.Failure(response.message.toString()))
                }
                is Resource.Success -> {
                    _state.emit(NetworkResult.Success(response.data!!))
                }
                is Resource.Exception -> Log.d("Response", response.message.toString())
            }
        }
    }
}