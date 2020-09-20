package com.sudo

import com.sudo.Constants.ACCESS_DENIED
import com.sudo.Constants.CSV
import com.sudo.Constants.INCORRECT_FILETYPE
import com.sudo.Constants.JSON
import com.sudo.Constants.NOT_ENOUGH_MEMORY
import com.sudo.Constants.SUCCESS
import com.sudo.Constants.UNEXPECTED_ERROR
import com.sudo.Constants.allowedMinimum
import com.sudo.Constants.categories
import com.sudo.Constants.writeToJson
import me.xdrop.fuzzywuzzy.FuzzySearch
import java.io.File
import java.io.FileReader
import java.io.IOException

fun main(args: Array<String>) {

    if (!args[0].endsWith(CSV)) {
        println(INCORRECT_FILETYPE)
        return
    }

    val fileContent = try {
        FileReader(File(args[0])).readText()
    } catch (e: IOException) {
        println(UNEXPECTED_ERROR)
        return
    } catch (e: OutOfMemoryError) {
        println(NOT_ENOUGH_MEMORY)
        return
    } catch (e: SecurityException) {
        println(ACCESS_DENIED)
        return
    }

    val lines = fileContent.replace(System.lineSeparator(), "").split(Regex("""[\da-f]{8};"""))

    val listOfCVs = mutableListOf<CV>()

    for (line in lines) {
        val fields = line.split(";").map { it.replace(Regex("""\r"""), "") }
        val name = categories.firstOrNull { FuzzySearch.ratio(it, fields[0]) > allowedMinimum } ?: continue
        val cv = CV(name, fields[1], fields[2])
        listOfCVs.add(cv)
    }

    writeToJson(".\\test$JSON", JsonModel(listOfCVs))

    println(SUCCESS)
}