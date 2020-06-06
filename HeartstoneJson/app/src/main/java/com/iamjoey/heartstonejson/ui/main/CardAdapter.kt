package com.iamjoey.heartstonejson.ui.main

import android.view.View
import android.view.ViewGroup
import android.content.Context
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import com.iamjoey.heartstonejson.R
import androidx.recyclerview.widget.RecyclerView
import com.iamjoey.heartstonejson.model.CardItem
import kotlinx.android.synthetic.main.card_item.view.*

class CardAdapter(private val cards: List<CardItem>,
                  private val onClick: (CardItem) -> Unit
) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cards[position], position+1)
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onClick(cards[adapterPosition])
            }
        }

        fun bind(cardItem: CardItem, number: Int) {
            Glide.with(context).load(cardItem.getCardImage()).into(itemView.ivPoster)
        }
    }

}