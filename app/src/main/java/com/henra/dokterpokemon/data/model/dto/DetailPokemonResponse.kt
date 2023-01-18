package com.henra.dokterpokemon.data.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailPokemonResponse(

	@Json(name="abilities")
	val abilities: List<AbilitiesItem?>? = null,

	@Json(name="types")
	val types: List<TypesItem?>? = null,

	@Json(name="stats")
	val stats: List<StatsItem?>? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="weight")
	val weight: Int? = null,

	@Json(name="id")
	val id: Int,

	@Json(name="sprites")
	val sprites: Sprites? = null,

	@Json(name="height")
	val height: Int? = null,

	@Json(name="order")
	val order: Int
)

@JsonClass(generateAdapter = true)
data class TypesItem(

	@Json(name="slot")
	val slot: Int? = null,

	@Json(name="type")
	val type: Type? = null
)

@JsonClass(generateAdapter = true)
data class Ability(

	@Json(name="name")
	val name: String? = null,

	@Json(name="url")
	val url: String? = null
)

@JsonClass(generateAdapter = true)
data class StatsItem(

	@Json(name="stat")
	val stat: Stat? = null,

	@Json(name="base_stat")
	val baseStat: Int? = null,

	@Json(name="effort")
	val effort: Int? = null
)

@JsonClass(generateAdapter = true)
data class Stat(

	@Json(name="name")
	val name: String? = null,

	@Json(name="url")
	val url: String? = null
)

@JsonClass(generateAdapter = true)
data class DreamWorld(

	@Json(name="front_default")
	val frontDefault: String? = null
)

@JsonClass(generateAdapter = true)
data class Other(

	@Json(name="dream_world")
	val dreamWorld: DreamWorld? = null
)

@JsonClass(generateAdapter = true)
data class AbilitiesItem(

	@Json(name="is_hidden")
	val isHidden: Boolean? = null,

	@Json(name="ability")
	val ability: Ability? = null,

	@Json(name="slot")
	val slot: Int? = null
)

@JsonClass(generateAdapter = true)
data class Type(

	@Json(name="name")
	val name: String? = null,

	@Json(name="url")
	val url: String? = null
)

@JsonClass(generateAdapter = true)
data class Sprites(

	@Json(name="other")
	val other: Other? = null
)