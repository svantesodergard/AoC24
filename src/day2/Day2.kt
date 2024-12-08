package day2

import java.io.File
import kotlin.math.abs
import kotlin.math.sign

val input = File("src/day2/test").readLines().toList()
val reports = input.map { it.split(" ").map { s -> s.trim().toInt() } }

fun List<Int>.step() = this.mapIndexed { index, level -> (((level - (this.getOrNull(index + 1) ?: 0)))) }



fun List<Int>.safeLevel() = this.all { step -> abs(step) in 1..3 } && this.all { step -> sign(step.toDouble()) == sign(this[0].toDouble()) }
fun bruteForce() {
    }

fun main() = bruteForce();