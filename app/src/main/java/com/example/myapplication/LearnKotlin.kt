package com.example.myapplication

import kotlin.math.max

fun main() {
    val a = 37
    val b = 40

//    println(largerNumber(a, b))

//    for (i in 0 until 10 step 2){
//        println(i)
//    }

    val list = mutableListOf<String>("apple","banana","orange","pear")
    list.add("watermelon")
    for (fruit in list) {
        println(fruit)
    }
}

fun largerNumber(num1: Int, num2: Int): Int {
    return max(num1, num2)
}

fun largerNumber2(num1: Int, num2: Int): Int = max(num1, num2)

fun largerNumber3(num1: Int, num2: Int): Int = if(num1 > num2) num1 else num2

fun getScore(name: String) = when(name) {
    "tom" -> 99
    "jim" -> 89
    "jack" -> 88
    else -> 0
}