package edu.karolinawidz.bestwishes.services

import edu.karolinawidz.bestwishes.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val KEY = BuildConfig.PEXELS_KEY
private const val BASE_URL = "https://api.pexels.com/v1/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface PictureApiService {
    @Headers("Authorization: $KEY")
    @GET("search?query=birthday")
    suspend fun getPictures(): String
}

object PictureApi {
    val retrofitService: PictureApiService by lazy {
        retrofit.create(PictureApiService::class.java)
    }
}
