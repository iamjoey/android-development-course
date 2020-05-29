package com.iamjoey.heartstonejson.ui.deck

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.iamjoey.heartstonejson.R
import android.view.LayoutInflater
import com.iamjoey.heartstonejson.model.Card
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card_item.view.*
import kotlinx.android.synthetic.main.deck_card_item.view.*
import kotlinx.android.synthetic.main.deck_card_item.view.ivPoster

class DeckCardAdapter(private val cards:List<Card>) : RecyclerView.Adapter<DeckCardAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
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
            Glide.with(context).load(card.getCardTile()).into(itemView.ivPoster)
        }
    }
}