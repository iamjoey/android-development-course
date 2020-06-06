package com.iamjoey.heartstonejson.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.iamjoey.heartstonejson.model.CardItem

class CardRepository(context: Context) {
    private val cardDao: CardDao

    init {
        val database = CardRoomDatabase.getDatabase(context)
        cardDao = database!!.cardDao()
    }

    fun findById(id: String): CardItem {
        return cardDao.findById(id)
    }

    fun getCards(): LiveData<List<CardItem>> {
        return cardDao.getCards()
    }

    suspend fun insertCard(cardItem: CardItem) {
        return cardDao.insertCard(cardItem)
    }

    suspend fun deleteCard(cardItem: CardItem) {
        return cardDao.deleteCard(cardItem)
    }

    suspend fun deleteAllCards() {
        return cardDao.deleteAllCards()
    }
}