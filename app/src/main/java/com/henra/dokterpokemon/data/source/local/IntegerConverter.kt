package com.henra.dokterpokemon.data.source.local

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.ParameterizedType

class IntegerConverter {
    private val moshi = Moshi.Builder().build()
    private val listMyData : ParameterizedType = Types.newParameterizedType(List::class.java, Integer::class.java)
    private val jsonAdapter: JsonAdapter<List<Int>> = moshi.adapter(listMyData)

    @TypeConverter
    fun toJson(list: List<Int>?): String? {
        return jsonAdapter.toJson(list)
    }

    @TypeConverter
    fun fromJson(json:String?):List<Int>?{
        return json?.let { jsonAdapter.fromJson(json) }
    }
}