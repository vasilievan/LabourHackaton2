package com.sudo

data class Waiter(
        val q1: String,
        val q2: Boolean,
        val q3: String,
        val q4: Int,
        val q5: Boolean,
        val q6: List<String>,
        val q7: List<String>,
        val q8: Map<String, String>,
        val q9: List<String>,
        val q10: List<Int>,
) : Worker {
    constructor(list: List<String>) {

    }
}