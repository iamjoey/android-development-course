package com.iamjoey.heartstonejson.ui.card

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.iamjoey.heartstonejson.model.CardItem
import com.iamjoey.heartstonejson.database.CardRepository

class CardDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val cardRepository = CardRepository(application.applicationContext)

    fun isAddedToDeck(id: String) : Boolean {
        var cardItem : CardItem? = cardRepository.findById(id)

        if (cardItem == null) {
            return false
        }

        return true
    }
}