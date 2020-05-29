package com.iamjoey.heartstonejson.ui.card

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.iamjoey.heartstonejson.database.CardRepository
import com.iamjoey.heartstonejson.model.Card

class CardDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val cardRepository = CardRepository(application.applicationContext)

    fun isAddedToDeck(id: String) : Boolean {
        var card : Card? = cardRepository.findById(id)

        if (card == null) {
            return true
        }

        return false
    }
}