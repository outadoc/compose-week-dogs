package com.example.androiddevchallenge.puppies.repository

import com.example.androiddevchallenge.puppies.model.PuppyBreed
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class PuppyRepositoryImpl : PuppyRepository {

    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
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