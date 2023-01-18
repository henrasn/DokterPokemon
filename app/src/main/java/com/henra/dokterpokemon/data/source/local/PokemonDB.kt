package com.henra.dokterpokemon.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.henra.dokterpokemon.data.model.uimodel.PokemonUiModel

@Database(entities = [PokemonUiModel::class,RemoteKey::class], version = 1)
@TypeConverters(PokemonEntityConverter::class,PokemonStatsEntityConverter::class,StringConverter::class,IntegerConverter::class)
abstract class PokemonDB :RoomDatabase(){
    abstract fun getPokemonDao():PokemonDao
    abstract fun getRemoteDao():RemoteKeyDao
}