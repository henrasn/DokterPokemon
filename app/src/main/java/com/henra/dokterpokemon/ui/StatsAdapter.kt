package com.henra.dokterpokemon.ui

import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.henra.dokterpokemon.data.model.uimodel.PokemonStats
import com.henra.dokterpokemon.databinding.ItemStatsBinding
import com.henra.dokterpokemon.utils.viewBinding

class StatsAdapter(
    private val dataSet: List<PokemonStats> = listOf(),
    @ColorInt private val color: Int
) : Adapter<StatsAdapter.StatsViewHolder>() {
    class StatsViewHolder(val binding: ItemStatsBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        return StatsViewHolder(parent.viewBinding(ItemStatsBinding::inflate))
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        with(holder.binding) {
            val stat = dataSet[position]

            tvStatsName.text = stat.name
            tvPercentage.text = stat.percentage.formatNumber()
            pbPercentage.setProgressCompat(stat.percentage,true)
            pbPercentage.setIndicatorColor(color)
            pbPercentage.trackColor=ColorUtils.setAlphaComponent(color, 65)

        }
    }

    override fun getItemCount(): Int = dataSet.size
}

