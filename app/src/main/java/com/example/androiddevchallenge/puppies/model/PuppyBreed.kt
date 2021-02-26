/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
