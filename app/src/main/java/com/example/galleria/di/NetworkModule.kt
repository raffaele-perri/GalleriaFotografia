package com.example.galleria.di

import com.example.galleria.BuildConfig
import com.example.galleria.services.APIInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASEURL = "https://api.punkapi.com/v2/"

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : APIInterface{
        return Retrofit.Builder().baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create()).
            client(okHttpClient).build().create(APIInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttp() : OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG)
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        else
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}