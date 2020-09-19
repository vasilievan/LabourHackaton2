package com.sudo

import com.google.gson.Gson
import java.io.FileWriter

data class JsonModel(val json: MutableList<Worker>)

fun writeToJson(path: String, model: JsonModel) =
    FileWriter(path).use { Gson().toJson(model, it) }
