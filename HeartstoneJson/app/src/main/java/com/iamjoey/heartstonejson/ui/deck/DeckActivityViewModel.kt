package com.iamjoey.heartstonejson.ui.deck

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iamjoey.heartstonejson.database.CardRepository
import com.iamjoey.heartstonejson.getJsonDataFromAsset
import com.iamjoey.heartstonejson.model.Card
import com.iamjoey.heartstonejson.model.CardPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class DeckActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val cardRepository = CardRepository(application.applicationContext)

    val cards: LiveData<List<Card>> = cardRepository.getCards()
}