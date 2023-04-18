package edu.karolinawidz.bestwishes.model.pexels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photos(
    val id: Int, val width: Int,
    val height: Int, val url: String,
    val src: Src,
    val alt: String,
)
