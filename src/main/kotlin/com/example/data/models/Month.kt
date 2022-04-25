package com.example.data.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class Month(

    var clothes: Float = 0.0f,
    var workout: Float = 0.0f,
    var remont: Float = 0.0f,
    var food: Food = Food(),
    var smetki: Smetki = Smetki(),
    var transport: Transport = Transport(),
    var posuda: Float = 0.0f,
    var travel: Float = 0.0f,
    var gifts: Float = 0.0f,
    var snacks: Float = 0.0f,
    var medicine: Float = 0.0f,
    var cosmetics: Cosmetics = Cosmetics(),
    var domPotrebi: Float = 0.0f,
    var preparati: Preparati = Preparati(),
    var machove: Float = 0.0f,
    var furniture: Float = 0.0f,
    var tehnika: Float = 0.0f,
    var education: Float = 0.0f,
    var entertainment: Float = 0.0f,
    var subscriptions: Float = 0.0f,
    var tattoo: Float = 0.0f,
    var toys: Float = 0.0f,
    @BsonId
    val id: String
)
