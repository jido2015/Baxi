package com.olajide.capricon.base.hilt

import com.olajide.capricon.base.DispatchProvider
import com.olajide.capricon.login.data.ApiService
import com.olajide.capricon.login.data.Datasource
import com.olajide.capricon.login.domain.Repository
import com.olajide.capricon.login.domain.usecase.implementation.LoginInteractionsImpl
import com.olajide.capricon.login.domain.usecase.interaction.LoginInteraction
import com.olajide.capricon.transactions.data.TrnxApiService
import com.olajide.capricon.transactions.data.TrnxDataSource
import com.olajide.capricon.transactions.domain.TrnxRepository
import com.olajide.capricon.transactions.domain.usecase.implementation.TrnxInteractionImpl
import com.olajide.capricon.transactions.domain.usecase.interaction.TrnxInteraction
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


    //Login ApiService
    @Singleton
    @Provides
    fun provideLoginRepository(api: ApiService):
            Repository = Datasource(api)


    //Providing Interactor for Login
    @Singleton
    @Provides
    fun provideLoginInteractor(repository: Repository): LoginInteraction =
        LoginInteractionsImpl(repository)


    @Singleton
    @Provides
    fun provideTrnxRepository(repository: TrnxApiService):
            TrnxRepository = TrnxDataSource(repository)

    @Singleton
    @Provides
    fun provideTrnxInteraction(repostory: TrnxRepository): TrnxInteraction =
        TrnxInteractionImpl(repostory)
}