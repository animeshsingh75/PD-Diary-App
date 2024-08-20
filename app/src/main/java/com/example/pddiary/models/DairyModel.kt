package com.example.pddiary.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DairyModel(
    var time: String,
    var asleep: Boolean,
    var on: Boolean,
    var onWithTroublesome: Boolean,
    var onWithoutTroublesome: Boolean,
    var off: Boolean,
    var med1Status: Boolean,
    var med2Status: Boolean,
    var measurement: Int?,
    override val listItemType: Int = DairyListItem.ROW,
) : DairyListItem, Parcelable
