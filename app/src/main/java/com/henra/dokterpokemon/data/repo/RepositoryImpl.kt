package com.henra.dokterpokemon.data.repo

import androidx.paging.PagingData
import com.henra.dokterpokemon.data.DataState
import com.henra.dokterpokemon.data.model.dto.Chain
import com.henra.dokterpokemon.data.model.dto.PokemonsResponse
import com.henra.dokterpokemon.data.model.uimodel.PokemonStats
import com.henra.dokterpokemon.data.model.uimodel.PokemonUiModel
import com.henra.dokterpokemon.data.onSuccess
import com.henra.dokterpokemon.data.source.remote.PokemonRemoteSource
import com.henra.dokterpokemon.utils.PageUtils
import com.henra.dokterpokemon.utils.Paging
import com.henra.dokterpokemon.utils.PagingDataModel
import com.henra.dokterpokemon.utils.lastPath
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteSource: PokemonRemoteSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : Repository {
    override suspend fun fetchPokemons(page: Int): Flow<PagingData<PokemonUiModel>> {
        return PageUtils.createPager {
            Paging { params ->
                var count = 0
                val data: List<PokemonUiModel>
                val error = Exception()

                val defaultOffset = PageUtils.FIRST_PAGE * PageUtils.NETWORK_PAGE_SIZE

                data = remoteSource.getEvolutionsFlow(
                    params.key ?: defaultOffset, PageUtils.NETWORK_PAGE_SIZE
                ).flatMapLatest { state ->
                    if (state is DataState.Success) {
                        count = state.data.count ?: 0
                        mapEvolutionResponse(state.data)
                    } else emptyFlow()
                }.flatMapMerge { evoId ->
                    var pokemonId = -1
                    var name = ""
                    var evoIds = listOf<Int>()
                    var image = ""
                    var orderNumb = 0
                    var speciesId = 0
                    var types: List<String> = listOf()
                    remoteSource.getDetailEvolutionFlow(evoId).onSuccess { response ->
                        val firstChain = response.chain
                        firstChain.species?.let { species ->
                            speciesId = species.url?.lastPath?.toIntOrNull() ?: -1
                            name = species.name.orEmpty()
                            evoIds = getEvoChainIds(mutableListOf(), firstChain)
                        }
                    }
                    remoteSource.getDetailSpeciesFlow(speciesId).onSuccess {
                        pokemonId =
                            it.varieties.getOrNull(0)?.pokemon?.url?.lastPath?.toIntOrNull()
                                ?: 0

                        orderNumb = it.order
                    }

                    var weight = 0
                    var height = 0
                    var abilities = listOf<String>()
                    var stats = listOf<PokemonStats>()

                    remoteSource.getDetailPokemonFlow(pokemonId).onSuccess {
                        image = it.sprites?.other?.dreamWorld?.frontDefault
                            ?: imageSourceUrl.format(pokemonId)
                        types = it.types?.mapNotNull { type -> type?.type?.name }.orEmpty()
                        weight = it.weight ?: 0
                        height = it.height ?: 0
                        abilities = it.abilities?.mapNotNull { it?.ability?.name }.orEmpty()
                        stats = it.stats?.mapNotNull { stat ->
                            PokemonStats(
                                stat?.stat?.name.orEmpty(), stat?.baseStat ?: 0
                            )
                        }.orEmpty()


                    }

                    flowOf(
                        PokemonUiModel(
                            pokemonId,
                            name,
                            image,
                            types,
                            "",
                            weight,
                            height,
                            abilities,
                            stats,
                            listOf(),
                            orderNumb
                        )
                    )
                }.toList()
                PagingDataModel(data, data.isNotEmpty(), count, error)
            }
        }.flow
    }

    private fun getEvoChainIds(evoIds: MutableList<Int>, chain: Chain): MutableList<Int> {
        if (chain.evolvesTo.isNullOrEmpty()) {
            return evoIds
        } else {
            val child = chain.evolvesTo.getOrNull(0)
            return child?.species?.let {
                val id = it.url?.lastPath?.toIntOrNull() ?: -1
                evoIds.add(id)
                getEvoChainIds(evoIds, child)
            } ?: return evoIds
        }
    }

    private fun mapEvolutionResponse(response: PokemonsResponse): Flow<Int> {
        return response.pokemons?.mapNotNull { pokemon ->
            pokemon?.let {
                pokemon.url?.lastPath?.toIntOrNull() ?: -1
            }
        }.orEmpty().asFlow()
    }
}

val imageSourceUrl =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/%d.png"