package com.iamjoey.heartstonejson.ui.deck

import android.view.View
import android.view.ViewGroup
import android.content.Context
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import com.iamjoey.heartstonejson.R
import androidx.recyclerview.widget.RecyclerView
import com.iamjoey.heartstonejson.model.CardItem
import kotlinx.android.synthetic.main.deck_card_item.view.*
import kotlinx.android.synthetic.main.deck_card_item.view.ivPoster

class DeckCardAdapter(private val cards:List<CardItem>) : RecyclerView.Adapter<DeckCardAdapter.ViewHolder>() {

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
        fun bind(cardItem: CardItem) {
            itemView.tvName.text = cardItem.name
            Glide.with(context).load(cardItem.getCardTile()).into(itemView.ivPoster)
        }
    }
}