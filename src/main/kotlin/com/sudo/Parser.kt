package com.sudo

import java.nio.file.Files
import java.nio.file.Paths

class Parser {
    fun parseFile(path: String) {
        val fileLines = Files.readAllLines(Paths.get(path))
        fileLines.forEach {
            val splitedFields = it.split(";")
            val model: JsonModel = vacancyType(splitedFields)
            writeToJson(path, model)
        }
    }

    private fun vacancyType(splitedFields: List<String>): JsonModel {
        TODO()
    }
}