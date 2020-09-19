package com.sudo

import java.nio.file.Files
import java.nio.file.Paths

class Parser {
    fun parseFile(path: String) {
        val fileLines = Files.readAllLines(Paths.get(path))
        val vacancyList = mutableListOf<Worker>()
        for (lines in fileLines) {
            val splitedFields = lines.split(";")
            val model = getWorkerFromFields(splitedFields)
            vacancyList.add(model ?: continue)
        }
        writeToJson(path, JsonModel(vacancyList))
    }

    private fun getWorkerFromFields(splitedFields: List<String>): Worker? =
            when (understandVacancy(splitedFields[1])) {
                "водитель" -> Driver(splitedFields)
                "кладовщик" -> Storekeeper(splitedFields)
                "официант" -> Waiter(splitedFields)
                else -> null
            }

    private fun understandVacancy(name: String): String {
        when {
            name.contains("официант", true) -> return "официант"
            name.contains("клад", true) -> return "кладовщик"
            name.contains("водит", true) -> return "водитель"
        }
        return ""
    }
}