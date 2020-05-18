package com.example.gamebacklog.ui.main

import java.util.*
import android.view.View
import android.view.ViewGroup
import com.example.gamebacklog.R
import java.text.SimpleDateFormat
import android.view.LayoutInflater
import com.example.gamebacklog.model.Game
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val games:List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            itemView.tvTitle.text = game.title
            itemView.tvPlatform.text = game.platform
            itemView.tvDate.text = getDateMonthYearString(game.releaseDate)
        }
    }

    private fun getDateMonthYearString(date: Date): String {
        return "Release: " + SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH).format(date)
    }
}