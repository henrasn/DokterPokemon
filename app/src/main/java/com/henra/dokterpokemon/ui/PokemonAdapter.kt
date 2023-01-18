package com.henra.dokterpokemon.ui

import android.graphics.Color
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.henra.dokterpokemon.data.model.uimodel.PokemonDiffUtil
import com.henra.dokterpokemon.data.model.uimodel.PokemonUiModel
import com.henra.dokterpokemon.databinding.ItemPokemonBinding
import com.henra.dokterpokemon.utils.loadImage
import com.henra.dokterpokemon.utils.speciesColor
import com.henra.dokterpokemon.utils.viewBinding

class PokemonAdapter(
    val onClick:(Int)->Unit
): PagingDataAdapter<PokemonUiModel, PokemonAdapter.PokemonViewHolder>(PokemonDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(parent.viewBinding(ItemPokemonBinding::inflate))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        getItem(position)?.let { pokemon->
            holder.bind(pokemon)
            holder.binding.root.setOnClickListener { onClick(position) }
        }
    }

    class PokemonViewHolder(val binding:ItemPokemonBinding):ViewHolder(binding.root) {
        fun bind(pokemon: PokemonUiModel) = with(binding){
            ivPokemon.loadImage(pokemon.image)
            tvName.text=pokemon.name
            tvNumber.text=pokemon.orderNumb.formatNumber("#")

            try {
                val color=Color.parseColor(speciesColor[pokemon.types.getOrNull(0)]?: speciesColor["normal"])
                binding.card.strokeColor=color
                binding.tvName.setBackgroundColor(color)
                binding.tvNumber.setTextColor(color)
            }catch (_:Exception){}
        }
    }
}

fun Int.formatNumber(prefix:String=""): String {
    return String.format("$prefix%03d",this)
}