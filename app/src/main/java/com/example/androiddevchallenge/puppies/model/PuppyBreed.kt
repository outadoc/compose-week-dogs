package com.example.androiddevchallenge.puppies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class PuppyBreed(

    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("image")
    val image: PuppyImage,

    @SerialName("temperament")
    val temperament: String? = null,

    @SerialName("bred_for")
    val bredFor: String? = null,

    @SerialName("breed_group")
    val breedGroup: String? = null,

    @SerialName("life_span")
    val lifeSpan: String? = null,

    @SerialName("origin")
    val origin: String? = null,

    @SerialName("country_code")
    val countryCode: String? = null,

    @SerialName("height")
    val height: PuppySize? = null,

    @SerialName("weight")
    val weight: PuppySize? = null,
) : Parcelable