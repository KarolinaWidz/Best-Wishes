package edu.karolinawidz.bestwishes.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo(
    val id: Int, val width: Int,
    val height: Int, val url: String,
    val src: Src,
    val alt: String,
)
