package com.olajide.capricon.hilt

import com.olajide.capricon.DispatchProvider
import com.olajide.capricon.login.data.LoginApiService
import com.olajide.capricon.login.data.LoginDatasource
import com.olajide.capricon.login.domain.LoginRepository
import com.olajide.capricon.login.domain.usecase.implementation.LoginInteractionsImpl
import com.olajide.capricon.login.domain.usecase.interactor.LoginInteraction
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDispatcher(): DispatchProvider = object : DispatchProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }


    @Singleton
    @Provides
    fun provideLoginRepository(api: LoginApiService):
            LoginRepository = LoginDatasource(api)

    @Singleton
    @Provides
    fun provideLoginInteractor(repository: LoginRepository): LoginInteraction =
        LoginInteractionsImpl(repository)

}