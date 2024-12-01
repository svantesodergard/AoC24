package day1

import java.io.File
import kotlin.math.abs

val input = File("src/day1/input").readLines().toList()

val leftList = input.map { it.substring(0, it.indexOf(' ')).trim().toInt() }
val rightList = input.map { it.substring(it.indexOf(' ')).trim().toInt() }

fun main() {
    part1()
    part2()
}

fun part2() {
    val counts = leftList.map { it to rightList.count { i -> i == it } }
    val similarityScores = counts.map { it.first * it.second }
    println( similarityScores.sum() )
}

fun part1() {
    val leftSorted = leftList.sorted()
    val rightSorted = rightList.sorted()

    val distances = leftSorted.mapIndexed { index, i -> abs(i - rightSorted[index]) }

    println(distances.sum())
}