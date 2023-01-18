package com.henra.dokterpokemon.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.henra.dokterpokemon.data.model.uimodel.PokemonUiModel
import com.henra.dokterpokemon.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    private val _uiState=MutableSharedFlow<HomeUiState>()
    val uiState=_uiState.asSharedFlow()

    fun getPokemons() {
        viewModelScope.launch {
            _uiState.emit(HomeUiState.Loading)
            repository.fetchPokemons(1)
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _uiState.emit(HomeUiState.Pokemons(pagingData))
                }
        }
    }
}

sealed class HomeUiState {
    data class Pokemons(val data: PagingData<PokemonUiModel>) : HomeUiState()
    data class Failed(val errorMessage: String) : HomeUiState()
    object Loading : HomeUiState()
}