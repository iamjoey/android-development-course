package com.example.reminder

import android.content.Context
import androidx.lifecycle.LiveData

public class ReminderRepository(context: Context) {
    private var reminderDao: ReminderDao

    init {
        val reminderRoomDatabase = ReminderRoomDatabase.getDatabase(context)
        reminderDao = reminderRoomDatabase!!.reminderDao()
    }

    fun getAllReminders(): LiveData<List<Reminder>> {
        return reminderDao.getAllReminders()
    }

    suspend fun insertReminder(reminder: Reminder) {
        return reminderDao.insertReminder(reminder)
    }

    suspend fun deleteReminder(reminder: Reminder) {
        return reminderDao.deleteReminder(reminder)
    }

    suspend fun updateReminder(reminder: Reminder) {
        return reminderDao.updateReminder(reminder)
    }
}