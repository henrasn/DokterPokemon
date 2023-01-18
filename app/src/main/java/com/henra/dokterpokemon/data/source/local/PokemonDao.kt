package com.henra.dokterpokemon.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.henra.dokterpokemon.data.model.uimodel.PokemonUiModel

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<PokemonUiModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemons: PokemonUiModel)

    @Query("Select * From pokemons Order By orderNumb")
    fun getPokemons(): PagingSource<Int, PokemonUiModel>

    @Query("Select * From pokemons WHERE id==:id")
    fun getPokemon(id:Int): PagingSource<Int, PokemonUiModel>

    @Query("Delete From pokemons")
    suspend fun clearAllPokemons()
}