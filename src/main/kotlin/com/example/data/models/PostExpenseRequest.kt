package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PostExpenseRequest(

    val monthId: String,
    val title: String,
    val price: Float
)
