package com.henra.dokterpokemon.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.henra.dokterpokemon.R
import com.henra.dokterpokemon.data.model.uimodel.PokemonUiModel
import com.henra.dokterpokemon.databinding.FragmentDetailPokemonBinding
import com.henra.dokterpokemon.utils.viewBinding

class DetailPokemonFragment : Fragment(R.layout.fragment_detail_pokemon) {

    companion object {
        fun instance(
            pokemon: List<PokemonUiModel>,
            selectedPosition: Int
        ): DetailPokemonFragment {
            return DetailPokemonFragment().apply {
                this.pokemon = pokemon
                this.selectedPosition = selectedPosition
            }
        }
    }

    private var selectedPosition = 1
    private var pokemon: List<PokemonUiModel>? = null
    private var getPrev: (() -> PokemonUiModel)? = null
    private var getNext: (() -> PokemonUiModel)? = null

    private val binding by viewBinding(FragmentDetailPokemonBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btnBack.setOnClickListener { activity?.onBackPressed() }
            page.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            pokemon?.let {
                val adapter = PokemonPageAdapter(it,
                    onNext = { page.setCurrentItem(page.currentItem.plus(1), true) },
                    onPrev = { page.setCurrentItem(page.currentItem.minus(1), true) })

                page.adapter = adapter
                page.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        val selected = adapter.dataSet[position]
                        tvTitle.text = selected.name.replaceFirstChar { it.uppercase() }
                        tvNumber.text = selected.orderNumb.formatNumber("#")
                    }
                })
                page.setCurrentItem(selectedPosition, false)
            }
        }
    }
}

