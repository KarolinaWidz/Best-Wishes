package edu.karolinawidz.bestwishes.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Src(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String
)
