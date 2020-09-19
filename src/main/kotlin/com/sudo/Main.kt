package com.sudo

fun main(args: Array<String>) {
    val path = args[0]
    val parser = Parser()
    parser.parseFile(path)
}
