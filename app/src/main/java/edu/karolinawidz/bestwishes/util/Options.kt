package edu.karolinawidz.bestwishes.util

import com.bumptech.glide.request.RequestOptions
import edu.karolinawidz.bestwishes.R

object Options {
    val glideOptions =
        RequestOptions().placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image)
}