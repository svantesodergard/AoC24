package day3

import java.io.File

val input = File("src/day3/input").readLines().toList()

fun part1() {
    val matches = "mul\\((\\d+),(\\d+)\\)".toRegex().findAll(input.joinToString() ).map { it.value }.toList()
    println( matches.sumOf { it.multiply() } )
}

fun main() = part2()
fun part2() {
    val matches = "do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\)".toRegex().findAll(input.joinToString() )
        .map { it.value }.toList().joinToString()
    val dos = matches.split('d').filter { !it.contains("on't()") }
    val toBeMulted = dos.map { it.substring(it.indexOf('m')) }.map { it.split(", ") }
    println(toBeMulted.flatten().filter { it.isNotBlank() }.sumOf { it.multiply() })
}

fun String.multiply(): Int {
    val commaIndex = this.indexOf(',')
    val factor1 = this.substring(this.indexOf('(') + 1, commaIndex).toInt()
    val factor2 = this.substring(commaIndex + 1, this.indexOf(')')).toInt()
    return factor1 * factor2;
}
