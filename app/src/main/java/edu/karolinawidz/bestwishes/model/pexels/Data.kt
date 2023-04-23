package edu.karolinawidz.bestwishes.model.pexels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val page: Int,
    @Json(name = "per_page") var perPage: Int,
    var photos: List<Photo>,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "next_page") val nextPage: String
)
