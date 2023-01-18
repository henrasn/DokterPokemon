package com.henra.dokterpokemon.data.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonsResponse(

	@Json(name="next")
	val next: String? = null,

	@Json(name="previous")
	val previous: String? = null,

	@Json(name="count")
	val count: Int? = null,

	@Json(name="results")
	val pokemons: List<PokemonItem?>? = null
)

@JsonClass(generateAdapter = true)
data class PokemonItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="url")
	val url: String? = null
)
