package com.henra.dokterpokemon.core

import android.content.Context
import androidx.room.Room
import com.henra.dokterpokemon.data.source.local.PokemonDB
import com.henra.dokterpokemon.data.source.local.PokemonDao
import com.henra.dokterpokemon.data.source.local.RemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Singleton
    @Provides
    fun provideIoCoroutineDispatcher() = Dispatchers.IO

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): PokemonDB =
        Room
            .databaseBuilder(context, PokemonDB::class.java, "movies_database")
            .build()

    @Singleton
    @Provides
    fun provideMoviesDao(pokemonDB: PokemonDB): PokemonDao = pokemonDB.getPokemonDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(pokemonDB: PokemonDB): RemoteKeyDao = pokemonDB.getRemoteDao()
}