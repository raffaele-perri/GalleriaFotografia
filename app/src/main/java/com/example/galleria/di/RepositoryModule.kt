package com.example.galleria.di

import com.example.app_data.implementation.BeerRepositoryImpl
import com.example.app_domain.repository.IBeerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun providePostRepository(repository : BeerRepositoryImpl) : IBeerRepository
}