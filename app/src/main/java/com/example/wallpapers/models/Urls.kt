package com.example.wallpapers.models

import java.io.Serializable

data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val small_s3: String,
    val thumb: String
): Serializable