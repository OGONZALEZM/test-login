package com.ogonzalezm.testlogin.di

import com.ogonzalezm.testlogin.network.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(): APIService {
        return API()
    }

}

class API: APIService