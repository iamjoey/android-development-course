package com.iamjoey.heartstonejson

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.AndroidViewModel
import com.iamjoey.heartstonejson.model.CardPage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iamjoey.heartstonejson.model.Card

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    val cardsPage = MutableLiveData<CardPage>()

    val error = MutableLiveData<String>()

    val progressBarStatus = MutableLiveData<Boolean>(false)

    fun getCards() {
        progressBarStatus.value = true

        val jsonFileString = getJsonDataFromAsset(getApplication(), "cards.collectible.json")

        val gson = Gson()
        val listCardType = object : TypeToken<List<Card>>() {}.type

        var cards: List<Card> = gson.fromJson(jsonFileString, listCardType)
        cardsPage.value = CardPage(cards)

        progressBarStatus.value = false
    }
}