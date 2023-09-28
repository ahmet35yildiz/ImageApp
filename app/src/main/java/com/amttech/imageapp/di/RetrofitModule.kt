package com.amttech.imageapp.di

import com.amttech.imageapp.retrofit.ApiUtils.BASE_URL
import com.amttech.imageapp.retrofit.ImagesInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideCharacterService(): ImagesInterface = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

        .build()
        .create(ImagesInterface::class.java)


}