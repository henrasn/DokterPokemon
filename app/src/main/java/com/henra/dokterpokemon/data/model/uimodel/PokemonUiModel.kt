package com.henra.dokterpokemon.data.model.uimodel

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "pokemons")
@JsonClass(generateAdapter = true)
data class PokemonUiModel(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val name:String,
    val image:String,
    val types:List<String>,
    val desc:String="",
    val weight:Int,
    val height:Int,
    val abilities:List<String>,
    val stats:List<PokemonStats>,
    val evolution:List<Int>,
    val orderNumb: Int
)


object PokemonDiffUtil : DiffUtil.ItemCallback<PokemonUiModel>() {
    override fun areItemsTheSame(oldItem: PokemonUiModel, newItem: PokemonUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PokemonUiModel, newItem: PokemonUiModel): Boolean {
        return oldItem == newItem
    }

}