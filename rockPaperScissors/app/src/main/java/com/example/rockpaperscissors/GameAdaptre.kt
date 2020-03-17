package com.example.rockpaperscissors


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.game_history_item.view.*

// manages the view holder objects
class GameAdaptre(private val games:List<Game>) : RecyclerView.Adapter<GameAdaptre.ViewHolder>() {

    // here we link the adapter to the layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game_history_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    // bind the view holder to the data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    // the class ViewHolder is responsible for displaying a single item form the list in a view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lose = 0;
        val draw = 1;
        val win = 2;

        fun bind(game: Game) {
            when (game.result) {
                lose -> itemView.tvResult.text = itemView.context.getString(R.string.computer_wins)
                draw -> itemView.tvResult.text = itemView.context.getString(R.string.draw)
                win -> itemView.tvResult.text = itemView.context.getString(R.string.you_win)
            }

            itemView.ivYou.setImageResource(Game.GAME_RES_DRAWABLES_IDS[game.player])
            itemView.ivComputer.setImageResource(Game.GAME_RES_DRAWABLES_IDS[game.computer])
            itemView.tvDate.text = game.date.toString()
        }
    }
}