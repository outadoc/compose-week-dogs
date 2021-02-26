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
package com.example.androiddevchallenge.puppies.repository

import com.example.androiddevchallenge.puppies.model.PuppyBreed
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.header
import kotlinx.serialization.json.Json

class PuppyRepositoryImpl : PuppyRepository {

    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    private companion object {
        const val baseUrl = "https://api.thedogapi.com/v1"
        const val apiKey = "1c7ac347-e211-4ca6-a743-aa39fe50fb45"
    }

    override suspend fun getBreeds(): List<PuppyBreed> {
        return client.get("$baseUrl/breeds") {
            header("X-Api-Key", apiKey)
        }
    }
}
