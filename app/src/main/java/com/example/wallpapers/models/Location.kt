package com.example.wallpapers.models

import java.io.Serializable

data class Location(
    val city: Any,
    val country: Any,
    val name: String,
    val position: Position,
    val title: String
):Serializable