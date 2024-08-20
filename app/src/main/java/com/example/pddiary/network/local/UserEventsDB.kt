package com.example.pddiary.network.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [UserEvent::class], version = 1)
@TypeConverters(com.example.pddiary.network.local.TypeConverters::class)
abstract class UserEventsDB : RoomDatabase() {
    abstract fun userEventDao(): UserEventDao
}