package day7

import java.io.File
import kotlin.math.pow

val input = File("src/day7/input").readLines().toList()

fun main() {
    part1()
    part2()
}

fun part2() {
    fun String.isPossible(): Boolean {
        val expectedResult = this.substring(0, this.indexOf(':')).toBigInteger()
        val numbers = this.substring(this.indexOf(':') + 2).split(' ').map { it.trim().toBigInteger() }

        //Prova alla olika operator-permutationer
        for (operator in 0 until 3.0.pow((numbers.size - 1)).toInt()) {
            //* representeras av 1 och + av 0 och || av 2
            val operators = operator.toBaseThreeString().padStart(numbers.size - 1, '0')

            var result = numbers[0]
            numbers.subList(1, numbers.size).forEachIndexed { i, number ->
                when (operators[i]) {
                    '0' -> result += number
                    '1' -> result *= number
                    '2' -> result = ("" + result + number).toBigInteger()
                }
            }

            if (result == expectedResult) {
                return true
            }
        }

        return false
    }

    println("Grab a coffee...")
    println( input.filter { it.isPossible() }.sumOf { it.substring(0, it.indexOf(':')).toBigInteger() } )
}

fun part1() {
    fun String.isPossible(): Boolean {
        val expectedResult = this.substring(0, this.indexOf(':')).toBigInteger()
        val numbers = this.substring(this.indexOf(':') + 2).split(' ').map { it.trim().toBigInteger() }

        //Prova alla olika operator-permutationer
        for (operator in 0 until 2.0.pow((numbers.size - 1)).toInt()) {
            //* representeras av 1 och + av 0
            val operators = Integer.toBinaryString(operator).padStart(numbers.size - 1, '0')

            var result = numbers[0]
            numbers.subList(1, numbers.size).forEachIndexed { i, number ->
                if (operators[i] == '0') {
                    result += number
                } else {
                    result *= number
                }
            }

            if (result == expectedResult) {
                return true
            }
        }

        return false
    }

    println( input.filter { it.isPossible() }.sumOf { it.substring(0, it.indexOf(':')).toBigInteger() } )
}

//Tack Geppe <3
fun Int.toBaseThreeString(): String {
    if (this == 0) return "0" // Handle the edge case where the this is 0

    var num = this
    val result = StringBuilder()

    while (num != 0) {
        result.append(num % 3) // Append the remainder (digit in base 3)
        num /= 3 // Update the number by dividing it by 3
    }

    return result.reverse().toString() // Reverse the result string to get the correct order
}