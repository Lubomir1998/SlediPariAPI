package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SimpleResponse(

    val isSuccessful: Boolean,
    val message: String
)
