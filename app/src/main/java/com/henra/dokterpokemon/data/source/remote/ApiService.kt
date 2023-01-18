package com.henra.dokterpokemon.data.source.remote

import com.henra.dokterpokemon.data.model.dto.DetailEvolutionResponse
import com.henra.dokterpokemon.data.model.dto.DetailPokemonResponse
import com.henra.dokterpokemon.data.model.dto.DetailSpeciesResponse
import com.henra.dokterpokemon.data.model.dto.PokemonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonsResponse>

    @GET("evolution-chain/")
    suspend fun getEvolution(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonsResponse>

    @GET("evolution-chain/{id}/")
    suspend fun getDetailEvolution(
        @Path("id") id: Int
    ): Response<DetailEvolutionResponse>

    @GET("pokemon-species/{id}/")
    suspend fun getDetailSpecies(
        @Path("id") id: Int
    ): Response<DetailSpeciesResponse>

    @GET("pokemon/{id}/")
    suspend fun getDetailPokemon(@Path("id") path: Int): Response<DetailPokemonResponse>
}