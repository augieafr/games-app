package com.augieafr.gamesapp.ui.games

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.augieafr.gamesapp.R
import com.augieafr.gamesapp.data.ApiResponse
import com.augieafr.gamesapp.databinding.FragmentDetailGameBinding
import com.augieafr.gamesapp.model.GameModel
import com.augieafr.gamesapp.model.GenresItem
import com.augieafr.gamesapp.ui.GamesViewModel
import com.augieafr.gamesapp.utils.FragmentAutoClearedBinding
import com.augieafr.gamesapp.utils.Utils
import com.augieafr.gamesapp.utils.gone
import com.augieafr.gamesapp.utils.visible
import com.squareup.picasso.Picasso
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailGameFragment : Fragment(R.layout.fragment_detail_game) {

    private var gameId: Int = 0
    private val binding by FragmentAutoClearedBinding(FragmentDetailGameBinding::bind)
    private val viewModel by activityViewModels<GamesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameId = arguments?.getInt(ID_KEY) ?: 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.getDetailGame(gameId)
    }

    private fun setupObserver() {
        viewModel.game.observe(viewLifecycleOwner){gameModel->
            when(gameModel) {
                ApiResponse.Empty -> errorAction()

                is ApiResponse.Error -> errorAction()
                is ApiResponse.Success -> setupUi(gameModel.data)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner){isLoading ->
            if (isLoading) binding.root.gone()
            else binding.root.visible()
        }
    }

    private fun errorAction() {
        lifecycleScope.launch {
            Utils.showToast(context, "Network error")
            delay(2000)
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupUi(gameModel: GameModel?) = with(binding) {
        gameModel?.let {
            val backgroundUrl = gameModel.backgroundImage ?: "inul"
            val releasedDate = if (!gameModel.released.isNullOrEmpty()) "(${gameModel.released})" else ""
            tvName.text = getString(R.string.game_name, gameModel.name, releasedDate)
            tvDescription.text = gameModel.description ?: "No Description"
            Picasso.get()
                .load(backgroundUrl)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imgBackground)
            tvRating.text = getString(R.string.game_rating, gameModel.rating?.toInt())

            val genre = gameModel.genres?.concat()
            tvGenre.text = getString(R.string.game_genre, genre)
        }
    }

    private fun List<GenresItem?>.concat() = this.joinToString("") { it?.name ?: "" }

    companion object {
        const val ID_KEY = "ID_KEY"
    }
}