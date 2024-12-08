package day5

import java.io.File
import java.util.*

val input = File("src/day5/input").readLines().toList()
val rules = input.filter { it.contains('|') }
    .map{ it.substring(0, it.indexOf('|')).toInt() to it.substring(it.indexOf('|') + 1).toInt() }
val orders = input.filter { it.contains(',') }.map { it.split(',').map { s -> s.toInt() } }

fun main() {
    part1()
    part2()
}

fun part1() =
    println(orders.filter { it.allowed() }.sumOf { it[it.size / 2] } )
fun part2() =
    println( orders.filter { !it.allowed() }.map { it.sort() }.sumOf { it[it.size / 2] } )

fun correctlyPlaced(page : Int, index : Int, list : List<Int>) =
    (rules.filter { r -> r.second == page }.map { r -> r.first }).containsAll(list.subList(0, index))

fun List<Int>.allowed() =
    this.mapIndexed { i, page -> correctlyPlaced(page, i, this) }.all { it }

fun List<Int>.sort() : List<Int> {
    val sorted = mutableListOf<Int>()
    this.forEachIndexed { i, page ->
        if (correctlyPlaced(page, i, this)) {
            sorted.add(page)
        } else {
            sorted.add((0 until i).reversed().first { newI -> correctlyPlaced(page, newI, sorted) }, page)
        }
    }
    return sorted
}