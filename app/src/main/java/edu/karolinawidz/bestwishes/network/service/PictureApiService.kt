package edu.karolinawidz.bestwishes.network.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import edu.karolinawidz.bestwishes.BuildConfig
import edu.karolinawidz.bestwishes.network.model.Data
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val KEY = BuildConfig.PEXELS_KEY
private const val BASE_URL = "https://api.pexels.com/v1/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PictureApiService {
    @Headers("Authorization: $KEY")
    @GET("search")
    suspend fun getPictures(@Query("page") page: Int, @Query("query") category: String): Data
}

object PictureApi {
    val retrofitService: PictureApiService by lazy {
        retrofit.create(PictureApiService::class.java)
    }
}
