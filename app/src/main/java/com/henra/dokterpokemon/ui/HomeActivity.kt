package com.henra.dokterpokemon.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.henra.dokterpokemon.R
import com.henra.dokterpokemon.databinding.ActivityHomeBinding
import com.henra.dokterpokemon.utils.GridSpacesItemDecoration
import com.henra.dokterpokemon.utils.dp
import com.henra.dokterpokemon.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val viewModel by viewModels<HomeViewModel>()
    private val binding by viewBinding(ActivityHomeBinding::inflate)
    private lateinit var pokemonAdapter: PokemonAdapter
    private var detailFragment: DetailPokemonFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        pokemonAdapter = PokemonAdapter { position -> openDetail(position) }

        binding.rvPokemon.apply {
            layoutManager = GridLayoutManager(this@HomeActivity, 3)
            addItemDecoration(GridSpacesItemDecoration(8.dp))
            adapter = pokemonAdapter
        }

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is HomeUiState.Failed -> {}
                    HomeUiState.Loading -> {}
                    is HomeUiState.Pokemons -> pokemonAdapter.submitData(state.data)
                }
            }
        }

        viewModel.getPokemons()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.isNotEmpty()) {
            detailFragment?.let { supportFragmentManager.beginTransaction().remove(it).commit() }
                ?: binding.frame.removeAllViews()
        } else super.onBackPressed()
    }

    fun openDetail(position: Int) {
        detailFragment = DetailPokemonFragment.instance(
            pokemonAdapter.snapshot().items, position
        ).also {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, it).commit()
        }

    }
}