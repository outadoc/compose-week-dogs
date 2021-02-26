package com.example.androiddevchallenge.puppies.repository

import com.example.androiddevchallenge.puppies.model.PuppyBreed

interface PuppyRepository {
    suspend fun getBreeds(): List<PuppyBreed>
}