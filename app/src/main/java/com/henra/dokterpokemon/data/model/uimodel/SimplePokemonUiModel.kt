package com.henra.dokterpokemon.data.model.uimodel

data class SimplePokemonUiModel(
    val id: Int,
    val name: String = "",
    val image: String = "",
    val color: String = "",
    val chainEvo: List<Int> = listOf(),
    val orderNumb: Int
)
