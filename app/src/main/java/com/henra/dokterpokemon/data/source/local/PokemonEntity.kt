package com.henra.dokterpokemon.data.source.local

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.henra.dokterpokemon.data.model.uimodel.PokemonStats
import com.squareup.moshi.JsonClass

@Entity(tableName = "pokemons")
@JsonClass(generateAdapter = true)
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val name:String,
    val image:String,
    val types:List<String>,
    val about:String="",
    val weight:Int,
    val height:Int,
    val abilities:List<String>,
    val stats:List<PokemonStats>,
    val evolution:List<PokemonEntity>,
    val orderNumb: Int
)

object MainDiffUtil : DiffUtil.ItemCallback<PokemonEntity>() {
    override fun areItemsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
        return oldItem == newItem
    }

}