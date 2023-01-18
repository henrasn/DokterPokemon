package com.henra.dokterpokemon.data.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailEvolutionResponse(

	@Json(name="chain")
	val chain: Chain ,

	@Json(name="id")
	val id: Int
)

@JsonClass(generateAdapter = true)
data class Species(

	@Json(name="name")
	val name: String? = null,

	@Json(name="url")
	val url: String? = null
)

@JsonClass(generateAdapter = true)
data class EvolvesToItem(

	@Json(name="species")
	val species: Species? = null,

	@Json(name="evolves_to")
	val evolvesTo: List<Any?>? = null,

	@Json(name="is_baby")
	val isBaby: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class Chain(

	@Json(name="species")
	val species: Species? = null,

	@Json(name="evolves_to")
	val evolvesTo: List<Chain?>? = null
)
