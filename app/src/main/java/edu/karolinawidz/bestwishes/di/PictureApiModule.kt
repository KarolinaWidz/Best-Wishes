package edu.karolinawidz.bestwishes.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import edu.karolinawidz.bestwishes.network.service.PictureApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
class PictureApiModule {

    companion object {
        const val BASE_URL = "https://api.pexels.com/v1/"
    }

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideRetrofit(moshi: Moshi, baseUrl: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseUrl)
            .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): PictureApiService =
        retrofit.create(PictureApiService::class.java)

}



