package com.example.androiddevchallenge.puppies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class PuppySize(

    @SerialName("metric")
    val metric: String
) : Parcelable