package com.iamjoey.heartstonejson.ui.deck

import android.app.Application
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.AndroidViewModel
import com.iamjoey.heartstonejson.model.CardItem
import com.iamjoey.heartstonejson.database.CardRepository

class DeckActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val cardRepository = CardRepository(application.applicationContext)

    val cards: LiveData<List<CardItem>> = cardRepository.getCards()

    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun deleteCard(cardItem: CardItem) {
        ioScope.launch {
            cardRepository.deleteCard(cardItem)
        }
    }

    fun deleteAllCards() {
        ioScope.launch { cardRepository.deleteAllCards() }
    }
}