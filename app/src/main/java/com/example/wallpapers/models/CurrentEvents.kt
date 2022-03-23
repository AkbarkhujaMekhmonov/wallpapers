package com.example.wallpapers.models

import java.io.Serializable

data class CurrentEvents(
    val approved_on: String,
    val status: String
): Serializable