package com.example.vklenta.di

import com.example.vklenta.data.repository.NewsfeedRepositoryImpl
import com.example.vklenta.domain.repository.NewsfeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {



    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: NewsfeedRepositoryImpl): NewsfeedRepository
}