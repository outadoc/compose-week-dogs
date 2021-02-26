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
package com.example.androiddevchallenge.puppies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.puppies.model.PuppyBreed
import com.example.androiddevchallenge.puppies.repository.PuppyRepository
import com.example.androiddevchallenge.puppies.repository.PuppyRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PuppyListViewModel : ViewModel() {

    sealed class State {
        object Loading : State()
        data class Error(val message: String) : State()
        data class Done(val breeds: List<PuppyBreed>) : State()
    }

    private val repository: PuppyRepository = PuppyRepositoryImpl()

    private val _state: MutableLiveData<State> = MutableLiveData()
    val state: LiveData<State>
        get() = _state

    init {
        loadPuppies()
    }

    fun loadPuppies() {
        _state.value = State.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val next = try {
                State.Done(repository.getBreeds())
            } catch (e: Exception) {
                State.Error(e.toString())
            }

            withContext(Dispatchers.Main) {
                _state.value = next
            }
        }
    }
}
