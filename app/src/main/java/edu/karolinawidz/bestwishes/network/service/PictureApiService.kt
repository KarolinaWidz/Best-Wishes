package edu.karolinawidz.bestwishes.network.service

import edu.karolinawidz.bestwishes.BuildConfig
import edu.karolinawidz.bestwishes.network.model.Data
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val KEY = BuildConfig.PEXELS_KEY

fun interface PictureApiService {
    @Headers("Authorization: $KEY")
    @GET("search")
    suspend fun getPictures(@Query("page") page: Int, @Query("query") category: String): Data
}

