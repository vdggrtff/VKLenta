package com.example.vklenta.di

import com.example.vklenta.data.network.ApiFactory
import com.example.vklenta.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryImplModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService = ApiFactory.apiService
}