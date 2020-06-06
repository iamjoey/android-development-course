package com.iamjoey.heartstonejson.database

import androidx.room.Room
import androidx.room.Database
import android.content.Context
import androidx.room.RoomDatabase
import com.iamjoey.heartstonejson.model.CardItem

@Database(entities = [CardItem::class], version = 1, exportSchema = false)
abstract class CardRoomDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao

    companion object {
        private const val DATABASE_NAME = "deck"

        @Volatile
        private var INSTANCE: CardRoomDatabase? = null

        fun getDatabase(context: Context) : CardRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(CardRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CardRoomDatabase::class.java, DATABASE_NAME
                        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                    }
                }
            }

            return INSTANCE
        }
    }
}