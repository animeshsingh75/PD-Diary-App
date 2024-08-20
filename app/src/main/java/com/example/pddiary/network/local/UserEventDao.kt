package com.example.pddiary.network.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserEventDao {

    @Insert
    suspend fun insertEvent(userEvent: UserEvent)

    @Query("SELECT * FROM user_events WHERE date = :givenDate AND eventType = :eventType LIMIT 1")
    suspend fun getEventByDateAndType(givenDate: String, eventType: String): UserEvent?

    @Query(
        """
    UPDATE user_events 
    SET eventTimestamps = :newTimestamps, 
        visitCount = visitCount + 1 
    WHERE date = :givenDate AND eventType = :eventType
"""
    )
    suspend fun updateEventTimestampsAndIncrementVisitCount(
        givenDate: String,
        eventType: String,
        newTimestamps: List<String>
    )


    @Query(
        """
    UPDATE user_events 
    SET
        visitCount = visitCount + 1 
    WHERE date = :givenDate AND eventType = :eventType
"""
    )
    suspend fun updateIncrementVisitCount(
        givenDate: String,
        eventType: String,
    )
}