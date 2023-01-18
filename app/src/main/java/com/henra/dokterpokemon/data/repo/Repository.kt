package com.henra.dokterpokemon.data.repo

import androidx.paging.PagingData
import com.henra.dokterpokemon.data.model.uimodel.PokemonUiModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun fetchPokemons(page:Int): Flow<PagingData<PokemonUiModel>>
}