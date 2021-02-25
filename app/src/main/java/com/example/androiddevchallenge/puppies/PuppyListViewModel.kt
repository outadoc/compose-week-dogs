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