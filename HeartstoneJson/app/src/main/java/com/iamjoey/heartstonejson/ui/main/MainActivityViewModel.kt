package com.iamjoey.heartstonejson.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.AndroidViewModel
import com.iamjoey.heartstonejson.model.CardPage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iamjoey.heartstonejson.database.CardRepository
import com.iamjoey.heartstonejson.getJsonDataFromAsset
import com.iamjoey.heartstonejson.model.Card
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    val cardsPage = MutableLiveData<CardPage>()

    val error = MutableLiveData<String>()

    private val cardRepository = CardRepository(application.applicationContext)

    val progressBarStatus = MutableLiveData<Boolean>(false)

    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun getCards() {
        progressBarStatus.value = true

        val jsonFileString = getJsonDataFromAsset(
            getApplication(),
            "cards.collectible.json"
        )

        val gson = Gson()
        val listCardType = object : TypeToken<List<Card>>() {}.type

        var cards: List<Card> = gson.fromJson(jsonFileString, listCardType)
        cardsPage.value = CardPage(cards)

        progressBarStatus.value = false
    }

    fun insertCard(card: Card) {
        ioScope.launch {
            cardRepository.insertCard(card)
        }
    }

    fun deleteCard(card: Card) {
        ioScope.launch {
            cardRepository.deleteCard(card)
        }
    }
}