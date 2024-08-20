package com.example.pddiary.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.pddiary.network.local.Datastore
import com.example.pddiary.models.DairyButtonModel
import com.example.pddiary.models.DairyListItem
import com.example.pddiary.models.DairyModel
import com.example.pddiary.network.local.UserEvent
import com.example.pddiary.network.local.UserEventDao
import com.example.pddiary.network.local.UserEventsDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DairyViewModel(private val application: Application) : AndroidViewModel(application) {


    private val myPreferences = Datastore(application)
    private var db = Room.databaseBuilder(
        application,
        UserEventsDB::class.java,
        "events_database"
    ).build()
    private var userEventDao: UserEventDao = db.userEventDao()
    fun saveData(value: String) {
        viewModelScope.launch {
            myPreferences.addStringToList(application, value)
        }
    }

    fun readData(): LiveData<List<String>> {
        return myPreferences.getStringList().asLiveData()
    }


    private val list = mutableListOf(
        DairyModel(
            "12AM-12:30AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "12:30AM-1AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "1AM-1:30AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "1:30AM-2AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "2AM-2:30AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "2:30AM-3AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "3AM-3:30AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "3:30AM-4AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "4AM-4:30AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "4:30AM-5AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "5AM-5:30AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "5:30AM-6AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "6AM-6:30AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "6:30AM-7AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "7AM-7:30AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "7:30AM-8AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "8AM-8:30AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "8:30AM-9AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "9AM-9:30AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "9:30AM-10AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "10AM-10:30AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "10:30AM-11AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "11AM-11:30AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "11:30AM-12PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "12PM-12:30PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "12:30PM-1PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "1PM-1:30PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "1:30PM-2PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "2PM-2:30PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "2:30PM-3PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "3PM-3:30PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "3:30PM-4PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "4PM-4:30PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "4:30PM-5PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "5PM-5:30PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "5:30PM-6PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "6PM-6:30PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "6:30PM-7PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "7PM-7:30PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "7:30PM-8PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "8PM-8:30PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "8:30PM-9PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "9PM-9:30PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "9:30PM-10PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "10PM-10:30PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "10:30PM-11PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "11PM-11:30PM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyModel(
            "11:30PM-12AM",
            asleep = false,
            on = false,
            onWithTroublesome = false,
            onWithoutTroublesome = false,
            off = false,
            med1Status = false,
            med2Status = false,
            measurement = 0
        ),
        DairyButtonModel(),
    )

    fun getDairyList(): MutableList<DairyListItem> {
        return list
    }

    fun resetDairyList() {
        for (dairyModel in list) {
            if (dairyModel is DairyModel) {
                dairyModel.asleep = false
                dairyModel.on = false
                dairyModel.onWithTroublesome = false
                dairyModel.onWithoutTroublesome = false
                dairyModel.off = false
                dairyModel.med1Status = false
                dairyModel.med2Status = false
                dairyModel.measurement = 0
            }
        }
    }

    fun setDairyList(dairyList: List<DairyModel>) {
        for (i in dairyList.indices) {
            list[i] = dairyList[i]
        }
    }

    fun queryIncrementOrInsertWithTimestamp(givenDate: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val event = userEventDao.getEventByDateAndType(givenDate, "edit")
            if (event != null) {
                val updatedTimestamps = event.eventTimestamps?.toMutableList()
                updatedTimestamps?.add(System.currentTimeMillis().toString())
                userEventDao.updateEventTimestampsAndIncrementVisitCount(
                    givenDate,
                    "edit",
                    updatedTimestamps ?: emptyList()
                )

            } else {
                val newUserEvent = UserEvent(
                    eventType = "edit",
                    eventTimestamps = listOf(System.currentTimeMillis().toString()),
                    visitCount = 1,
                    date = givenDate,
                )
                userEventDao.insertEvent(newUserEvent)
            }
        }
    }

    fun queryIncrement() {
        viewModelScope.launch(Dispatchers.IO) {
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val todayDate = dateFormat.format(Date()).toString()
            val event = userEventDao.getEventByDateAndType(todayDate, "report_symptoms")
            if (event != null) {
                userEventDao.updateIncrementVisitCount(todayDate, "report_symptoms")
            } else {
                val newUserEvent = UserEvent(
                    eventType = "report_symptoms",
                    visitCount = 1,
                    date = todayDate,
                )
                userEventDao.insertEvent(newUserEvent)
            }
        }
    }

    fun insertScreenViewedDuration(screenName: String, durationSeconds: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            userEventDao.insertEvent(
                UserEvent(
                    eventType = "screen_viewed",
                    screenName = screenName,
                    durationSeconds = durationSeconds,
                    date = dateFormat.format(Date()).toString()
                )
            )
        }
    }

    fun buttonClicked(screenName: String, buttonName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            userEventDao.insertEvent(
                UserEvent(
                    eventType = "button_clicked",
                    buttonClickedName = buttonName,
                    screenName = screenName,
                    date = dateFormat.format(Date()).toString()
                )
            )
        }
    }


}