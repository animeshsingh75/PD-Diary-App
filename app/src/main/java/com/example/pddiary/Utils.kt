package com.example.pddiary


import android.util.Log
import com.example.pddiary.models.DairyListItem
import com.example.pddiary.models.DairyModel
import java.io.File
import java.io.FileWriter
import java.io.IOException

object Utils {

    fun saveDataToCSV(
        headers: List<String>,
        data: MutableList<DairyListItem>,
        file: File
    ) {
        try {
            val fileWriter = FileWriter(file)

            fileWriter.append(headers.joinToString(","))
            fileWriter.append("\n")

            if (data.isNotEmpty()) {
                for (dairyModel in data) {
                    if (dairyModel is DairyModel) {
                        fileWriter.append("${dairyModel.time},${dairyModel.asleep},${dairyModel.on},${dairyModel.onWithTroublesome},${dairyModel.onWithoutTroublesome},${dairyModel.off},${dairyModel.med1Status},${dairyModel.med2Status},${dairyModel.measurement}")
                        fileWriter.append("\n")
                    }
                }
            } else {
                Log.i("data", "List is empty")
            }
            fileWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun parseCsvToDairyModel(filePath: String): List<DairyModel> {
        val dairyModelList = mutableListOf<DairyModel>()
        File(filePath).useLines { lines ->
            val iterator = lines.iterator()
            if (iterator.hasNext()) {
                iterator.next()
            }
            for (line in iterator) {
                val columns = line.split(",")
                val dairyModel = DairyModel(
                    time = columns[0],
                    asleep = columns[1].toBoolean(),
                    on = columns[2].toBoolean(),
                    onWithTroublesome = columns[3].toBoolean(),
                    onWithoutTroublesome = columns[4].toBoolean(),
                    off = columns[5].toBoolean(),
                    med1Status = columns[6].toBoolean(),
                    med2Status = columns[7].toBoolean(),
                    measurement = if (columns[8] == "null") 0 else columns[8].toInt()
                )
                dairyModelList.add(dairyModel)
            }
        }

        return dairyModelList
    }
}