package com.iamjoey.heartstonejson.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.iamjoey.heartstonejson.model.Card

class CardRepository(context: Context) {
    private val cardDao: CardDao

    init {
        val database = CardRoomDatabase.getDatabase(context)
        cardDao = database!!.cardDao()
    }

    fun findById(id: String): Card {
        return cardDao.findById(id)
    }

    fun getCards(): LiveData<List<Card>> {
        return cardDao.getCards()
    }

    suspend fun insertCard(card: Card) {
        return cardDao.insertCard(card)
    }

    suspend fun deleteCard(card: Card) {
        return cardDao.deleteCard(card)
    }

    suspend fun deleteAllCards() {
        return cardDao.deleteAllCards()
    }
}