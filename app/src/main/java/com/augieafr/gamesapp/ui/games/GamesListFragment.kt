package com.augieafr.gamesapp.ui.games

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.augieafr.gamesapp.R
import com.augieafr.gamesapp.data.ApiResponse
import com.augieafr.gamesapp.databinding.FragmentGamesListBinding
import com.augieafr.gamesapp.model.GameModel
import com.augieafr.gamesapp.ui.GamesViewModel
import com.augieafr.gamesapp.utils.Utils
import com.augieafr.gamesapp.utils.gone
import com.augieafr.gamesapp.utils.viewBindingWithBinder
import com.augieafr.gamesapp.utils.visible

class GamesListFragment : Fragment(R.layout.fragment_games_list), GamesAdapter.Listener {

    private lateinit var domainType: GamesDomainType
    private val binding by viewBindingWithBinder(FragmentGamesListBinding::bind)
    private val viewModel: GamesViewModel by activityViewModels()
    private lateinit var adapter: GamesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        domainType = arguments?.getSerializable("domainType") as GamesDomainType
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.gameList.observe(viewLifecycleOwner) { apiResponse ->
                when(apiResponse){
                    ApiResponse.Empty -> Utils.showToast(context, "Data empty")
                    is ApiResponse.Error -> Utils.showToast(context, apiResponse.errorMessage)
                    is ApiResponse.Success -> adapter.setData(apiResponse.data)
                }
            }

        viewModel.isLoading.observe(viewLifecycleOwner){
            if (it) binding.rvGame.gone() else binding.rvGame.visible()
        }
    }

    private fun setupUi() = with(binding) {
        viewModel.getGameList(domainType)
        adapter = GamesAdapter(this@GamesListFragment)
        rvGame.layoutManager = LinearLayoutManager(context)
        rvGame.adapter = adapter
    }

    override fun onItemClick(gameId: Int?) {
        val bundle = Bundle()
        bundle.putInt(DetailGameFragment.ID_KEY, gameId ?: 0)
        findNavController().navigate(R.id.action_navigation_newest_to_detailGameFragment, bundle)
    }
}