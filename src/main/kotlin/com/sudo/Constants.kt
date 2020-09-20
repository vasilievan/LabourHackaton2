package com.sudo

import com.google.gson.Gson
import java.io.FileWriter

object Constants {
    const val CSV = ".csv"
    const val JSON = ".json"
    const val allowedMinimum = 40
    const val INCORRECT_FILETYPE = "Некорректный тип файла с резюме. Используйте только .csv"
    const val UNEXPECTED_ERROR = "Произошла неизвестная ошибка."
    const val NOT_ENOUGH_MEMORY = "Недостаточно оперативной памяти."
    const val ACCESS_DENIED = "Доступ запрещён."
    const val SUCCESS = "Успех."

    val categories = setOf("официант", "водитель погрузчика", "кладовщик")

    fun writeToJson(path: String, model: JsonModel) = FileWriter(path).use { Gson().toJson(model, it) }
}