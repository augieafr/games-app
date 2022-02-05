package com.augieafr.gamesapp.ui.games

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.augieafr.gamesapp.R
import com.augieafr.gamesapp.databinding.FragmentGamesListBinding
import com.augieafr.gamesapp.utils.viewBindingWithBinder

class GamesListFragment : Fragment(R.layout.fragment_games_list) {

    private lateinit var domainType: GamesDomainType
    private val binding by viewBindingWithBinder(FragmentGamesListBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        domainType = arguments?.getSerializable("domainType") as GamesDomainType
    }
}