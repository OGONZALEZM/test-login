package com.ogonzalezm.testlogin.di

import android.app.Application
import android.content.Context
import com.ogonzalezm.testlogin.network.APIService
import com.ogonzalezm.testlogin.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideLoginRepository (service: APIService): LoginRepository {
        return LoginRepository(service)
    }

}