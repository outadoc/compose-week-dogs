package com.example.androiddevchallenge.puppies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class PuppyImage(

    @SerialName("id")
    val id: String,

    @SerialName("url")
    val url: String,

    @SerialName("height")
    val height: Float,

    @SerialName("width")
    val width: Float
) : Parcelable {

    val aspectRatio: Float
        get() = width / height
}