package com.sudo

import com.google.gson.Gson
import java.io.FileWriter

data class JsonModel(val json: List<Worker>)

fun writeToJson(path: String, model: JsonModel) {
    val fw = FileWriter(path)
    Gson().toJson(model, fw)
    fw.flush()
    fw.close()
}