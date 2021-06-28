package com.example.galleria.di

import com.example.app_data.database.IDatabaseDataSource
import com.example.app_data.networking.INetworkDataSource
import com.example.galleria.framework.database.DatabaseDataSourceImpl
import com.example.galleria.framework.network.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DatabaseModule {
    @Binds
    @Singleton
    abstract fun provideDatabaseDataSource(ds: DatabaseDataSourceImpl) : IDatabaseDataSource
}