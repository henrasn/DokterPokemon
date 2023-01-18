package com.henra.dokterpokemon.data.source.local

import androidx.room.TypeConverter
import com.henra.dokterpokemon.data.model.uimodel.PokemonStats
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.ParameterizedType

class StringConverter {
    private val moshi = Moshi.Builder().build()
    private val listMyData : ParameterizedType = Types.newParameterizedType(List::class.java, PokemonStats::class.java)
    private val jsonAdapter: JsonAdapter<List<PokemonStats>> = moshi.adapter(listMyData)

    @TypeConverter
    fun toJson(list: List<PokemonStats>?): String? {
        return jsonAdapter.toJson(list)
    }

    @TypeConverter
    fun fromJson(json:String?):List<PokemonStats>?{
        return json?.let { jsonAdapter.fromJson(json) }
    }
}