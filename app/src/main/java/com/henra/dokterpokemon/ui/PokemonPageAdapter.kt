package com.henra.dokterpokemon.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.henra.dokterpokemon.R
import com.henra.dokterpokemon.data.model.uimodel.PokemonUiModel
import com.henra.dokterpokemon.databinding.ItemDetailPokemonBinding
import com.henra.dokterpokemon.utils.isVisible
import com.henra.dokterpokemon.utils.loadImage
import com.henra.dokterpokemon.utils.speciesColor
import com.henra.dokterpokemon.utils.viewBinding

class PokemonPageAdapter(
    val dataSet: List<PokemonUiModel>,
    val onNext: (View) -> Unit,
    val onPrev: (View) -> Unit
) : RecyclerView.Adapter<PokemonPageAdapter.PokemonPageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonPageViewHolder {
        return PokemonPageViewHolder(parent.viewBinding(ItemDetailPokemonBinding::inflate))
    }

    override fun onBindViewHolder(holder: PokemonPageViewHolder, position: Int) {
        val pokemon = dataSet[position]
        with(holder.binding) {

            btnNext.isVisible = position < dataSet.size
            btnPrev.isVisible = position > 0

            ivPokemon.loadImage(pokemon.image)
            tvWeight.text = "${pokemon.weight.div(10f)} kg"
            tvHeight.text = "${pokemon.height.div(10f)} m"
            tvAbilities.text = pokemon.abilities.joinToString("\n")
            tvDesc.text=pokemon.desc

            cpType.removeAllViews()
            pokemon.types.forEach { type ->
                val typeChip = Chip(cpType.context).apply {
                    setTextAppearanceResource(R.style.chipText)
                    text = type.replaceFirstChar { it.uppercase() }
                    chipBackgroundColor =
                        ColorStateList.valueOf(
                            Color.parseColor(speciesColor[type])
                        )
                }
                cpType.addView(typeChip)
            }

            try {
                val color = Color.parseColor(
                    speciesColor[pokemon.types.getOrElse(0) { "normal" }]
                )
                holder.binding.root.setBackgroundColor(color)
                tvAbout.setTextColor(color)
                tvBaseStats.setTextColor(color)

                rvStats.apply {
                    layoutManager = LinearLayoutManager(this.context)
                    adapter = StatsAdapter(pokemon.stats, color)
                }
            } catch (_: Exception) { }
        }
    }

    override fun getItemCount(): Int = dataSet.size

    inner class PokemonPageViewHolder(val binding: ItemDetailPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnNext.setOnClickListener(onNext)
            binding.btnPrev.setOnClickListener(onPrev)
        }
    }
}