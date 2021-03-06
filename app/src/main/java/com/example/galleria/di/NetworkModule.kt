package com.example.galleria.di

import com.example.app_data.networking.INetworkDataSource
import com.example.galleria.framework.network.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {
    @Binds
    @Singleton
    abstract fun provideNetworkDataSource(ds: NetworkDataSourceImpl) : INetworkDataSource
}