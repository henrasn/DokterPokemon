package com.henra.dokterpokemon.data.model.uimodel

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonStats(
    val name: String,
    val percentage: Int,
)