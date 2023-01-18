package com.henra.dokterpokemon.data.source.remote

import com.henra.dokterpokemon.data.DataState
import com.henra.dokterpokemon.data.model.dto.DetailEvolutionResponse
import com.henra.dokterpokemon.data.model.dto.DetailPokemonResponse
import com.henra.dokterpokemon.data.model.dto.DetailSpeciesResponse
import com.henra.dokterpokemon.data.model.dto.PokemonsResponse
import com.henra.dokterpokemon.utils.fetchApi
import com.henra.dokterpokemon.utils.fetchApiFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRemoteSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getPokemonsFlow(offset: Int, limit: Int): Flow<DataState<PokemonsResponse>> {
        return fetchApiFlow { apiService.getPokemons(offset,limit) }
    }

    suspend fun getEvolutionsFlow(offset: Int, limit: Int): Flow<DataState<PokemonsResponse>> {
        return fetchApiFlow { apiService.getEvolution(offset,limit) }
    }

    suspend fun getDetailEvolutionFlow(id:Int): DataState<DetailEvolutionResponse> {
        return fetchApi { apiService.getDetailEvolution(id) }
    }

    suspend fun getDetailSpeciesFlow(id:Int): DataState<DetailSpeciesResponse> {
        return fetchApi { apiService.getDetailSpecies(id) }
    }

    suspend fun getDetailPokemonFlow(id:Int): DataState<DetailPokemonResponse> {
        return fetchApi { apiService.getDetailPokemon(id) }
    }
}