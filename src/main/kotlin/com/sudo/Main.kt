package com.sudo

import com.google.gson.stream.JsonWriter
import me.xdrop.fuzzywuzzy.FuzzySearch
import java.io.File
import java.io.FileReader
import java.nio.file.Files
import java.nio.file.Paths

fun main() {

    val lines = FileReader(File("C:\\Users\\eugen\\Desktop\\hackaton\\resume_to_hackaton.csv"))
            .readText().replace(System.lineSeparator(), "").split("[\\da-f]{8};".toRegex())
            //.split("$[^-]{4,8}\\-".toRegex())
    val listOfCV = mutableListOf<CV>()
    for (line in lines) {
        val fields = line.split(";").map { it.replace("\\r".toRegex(), "") }
        val name = listOf("официант", "водитель погрузчика", "кладовщик")
                .firstOrNull { FuzzySearch.ratio(it, fields[0]) > 40 }
                ?: continue
        val cv = CV(name, fields[1], fields[2])
        listOfCV.add(cv)
    }
    writeToJson(".\\test2.json", JsonModel(listOfCV))
}
