package com.iamjoey.heartstonejson.ui.main

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.app.Application
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.AndroidViewModel
import com.iamjoey.heartstonejson.model.Card
import com.iamjoey.heartstonejson.model.CardItem
import com.iamjoey.heartstonejson.api.HeartstoneRepository
import com.iamjoey.heartstonejson.database.CardRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val heartstoneRepository = HeartstoneRepository(application.applicationContext)

    val cardsPage = MutableLiveData<Card>()

    val error = MutableLiveData<String>()

    val progressBarStatus = MutableLiveData<Boolean>(false)

    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val cardRepository = CardRepository(application.applicationContext)

    fun getCards() {

        progressBarStatus.value = true

        val call: Call<Card> = heartstoneRepository.getCards()

        call.enqueue(object : Callback<Card> {
            override fun onResponse(call: Call<Card>, response: Response<Card>) {
                if (response.isSuccessful) cardsPage.value = response.body()
                else error.value = "An error occurred: ${response.errorBody().toString()}"
                progressBarStatus.value = false
            }

            override fun onFailure(call: Call<Card>, t: Throwable) {
                error.value = t.message
                progressBarStatus.value = false
            }
        })
    }

    fun insertCard(cardItem: CardItem) {
        ioScope.launch {
            cardRepository.insertCard(cardItem)
        }
    }

    fun deleteCard(cardItem: CardItem) {
        ioScope.launch {
            cardRepository.deleteCard(cardItem)
        }
    }
}