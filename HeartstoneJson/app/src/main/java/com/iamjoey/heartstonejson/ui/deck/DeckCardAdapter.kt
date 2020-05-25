package com.iamjoey.heartstonejson.ui.deck

import android.view.View
import android.view.ViewGroup
import com.iamjoey.heartstonejson.R
import android.view.LayoutInflater
import com.iamjoey.heartstonejson.model.Card
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.deck_card_item.view.*

class DeckCardAdapter(private val cards:List<Card>) : RecyclerView.Adapter<DeckCardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.deck_card_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cards[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(card: Card) {
            itemView.tvName.text = card.name
        }
    }
}