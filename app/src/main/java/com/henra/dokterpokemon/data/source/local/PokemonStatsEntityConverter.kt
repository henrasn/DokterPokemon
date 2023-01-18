package com.henra.dokterpokemon.data.source.local

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.ParameterizedType

class PokemonStatsEntityConverter {
    private val moshi = Moshi.Builder().build()
    private val listMyData : ParameterizedType = Types.newParameterizedType(List::class.java, String::class.java)
    private val jsonAdapter: JsonAdapter<List<String>> = moshi.adapter(listMyData)

    @TypeConverter
    fun toJson(list: List<String>?): String? {
        return jsonAdapter.toJson(list)
    }

    @TypeConverter
    fun fromJson(json:String?):List<String>?{
        return json?.let { jsonAdapter.fromJson(json) }
    }
}