package day4

import java.io.File

val input = File("src/day4/input").readLines().toList()
val twoDimensionalInput = input.map { it.toCharArray() }
val xmas = "XMAS"
val xCoords = mutableListOf<Pair<Int, Int>>()

fun main() {
    part1()
    part2()
}

fun part2() {
    val aCoords = mutableListOf<Pair<Int, Int>>()
    twoDimensionalInput.forEachIndexed { y, row -> row.forEachIndexed { x, c ->
        if (c == 'A') aCoords.add(Pair(x, y))
    } }

    println( aCoords.count { it.xmas() } )
}

fun Pair<Int, Int>.xmas(): Boolean {
    return (this.mas(1, 1) && this.mas(1, -1) )
}

fun Pair<Int, Int>.mas(dx : Int, dy : Int): Boolean {
    val first = twoDimensionalInput.getOrNull(this.second + dy)?.getOrNull(this.first + dx) ?: ""
    val second = twoDimensionalInput.getOrNull(this.second - dy)?.getOrNull(this.first - dx) ?: ""
    return ""+first + second == "MS" || ""+first + second == "SM"
}

fun part1() {
    twoDimensionalInput.forEachIndexed { y, row -> row.forEachIndexed { x, c ->
        if (c == 'X') xCoords.add(Pair(x, y))
    } }
    println(
    xCoords.sumOf { (-1..1).sumOf { dx -> (-1..1)
        .count { dy -> twoDimensionalInput[it.second][it.first].xmas(it.first, it.second, dx, dy) } }}
    )
}

fun Char.xmas(x : Int, y: Int, dx : Int, dy :Int) : Boolean {
    val nextChar = twoDimensionalInput.getOrNull(y + dy)?.getOrNull(x + dx) ?: return false
    if (this == 'A' && nextChar == 'S') return true
    if (xmas.indexOf(this) != xmas.indexOf(nextChar) - 1) return false
    return nextChar.xmas(x + dx, y + dy, dx, dy)
}