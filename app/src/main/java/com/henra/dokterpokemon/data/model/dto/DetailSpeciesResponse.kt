package com.henra.dokterpokemon.data.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailSpeciesResponse(

	@Json(name="color")
	val color: Color? = null,

	@Json(name="name")
	val name: String = "",

	@Json(name="varieties")
	val varieties: List<PokemonVariety> = listOf(),

	@Json(name="order")
	val order: Int,

	@Json(name="form_descriptions")
	val formDescriptions: List<FormDescription>?=null
)

@JsonClass(generateAdapter = true)
data class Color(

	@Json(name="name")
	val name: String? = null,

	@Json(name="url")
	val url: String? = null
)

@JsonClass(generateAdapter = true)
data class PokemonVariety(

	@Json(name="pokemon")
	val pokemon: Pokemon? = null,

	@Json(name="is_default")
	val isDefault: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class Pokemon(

	@Json(name="name")
	val name: String? = null,

	@Json(name="url")
	val url: String? = null
)

@JsonClass(generateAdapter = true)
data class FormDescription(

	@Json(name="description")
	val description: String? = null,

	@Json(name="language")
	val language: Language? = null
)

@JsonClass(generateAdapter = true)
data class Language(

	@Json(name="name")
	val name: String? = null,

	@Json(name="url")
	val url: String? = null
)