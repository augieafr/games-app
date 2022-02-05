package com.augieafr.gamesapp.ui.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.augieafr.gamesapp.R
import com.augieafr.gamesapp.databinding.ItemGamesListBinding
import com.augieafr.gamesapp.model.GameModel
import com.squareup.picasso.Picasso

class GamesAdapter(private val listener: Listener) : RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    private val listGame = ArrayList<GameModel>()

    fun setData(data: List<GameModel>?) {
        if (data == null) return
        listGame.clear()
        listGame.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGamesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(listGame[position])
    }

    override fun getItemCount(): Int = listGame.size

    inner class GameViewHolder(private val binding: ItemGamesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(game: GameModel) {
            binding.tvName.text = game.name ?: "No Name"
            val backgroundUrl = game.backgroundImage ?: "null gan"
            Picasso.get()
                .load(backgroundUrl)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(binding.imgBackground)
        }
    }

    interface Listener {
        fun onItemClick(gameId: Int)
    }
}