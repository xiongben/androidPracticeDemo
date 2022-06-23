package com.example.myapplication

import java.lang.StringBuilder
import kotlin.math.max
import kotlin.reflect.KProperty

fun main() {
    val a = 37
    val b = 40

//    println(largerNumber(a, b))

//    for (i in 0 until 10 step 2){
//        println(i)
//    }

//    val list = mutableListOf<String>("apple","banana","orange","pear")
//    list.add("watermelon")
//    for (fruit in list) {
//        println(fruit)
//    }

    val num1 = 100
    val num2 = 70
    val res1 = num1AndNum2(num1, num2, ::plus)
    val res2 = num1AndNum2(num1, num2, ::minus)
    val res3 = num1AndNum2(num1, num2) {n1, n2 ->
        n1 + n2
    }
    println(res1)
    println(res2)
    println(res3)

    val list = listOf<String>("apple","orange","banana")
    val res4 = StringBuilder().build {
        append("start eating fruit.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        append("Ate all fruits.\n")
    }
    println(res4)
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


// 高阶函数练习
fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    return operation(num1, num2)
}

fun plus(num1: Int, num2: Int) : Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int) : Int {
    return num1 - num2
}

fun StringBuilder.build(block: StringBuilder.() -> Unit) : StringBuilder {
    block()
    return this
}

// 泛型
class MyClass2 {
    fun <T: Number> method(param: T):T {
        return param
    }
}

class MySet<T>(val helperSet: HashSet<T>): Set<T> by helperSet {
    override fun isEmpty(): Boolean {
        return false
    }
}

class MyClass {
    var p by Delegate()
}

class Delegate {
    var propValue: Any? = null

    operator fun getValue(myClass: MyClass, prop: KProperty<*>): Any? {
        return propValue
    }

    operator fun setValue(myClass: MyClass, prop: KProperty<*>, value: Any?) {
        propValue = value
    }
}

class Later<T>(val block:() -> T) {
    var value: Any? = null

    operator fun getValue(any: Any?, prop: KProperty<*>): T {
        if(value == null) {
            value = block()
        }
        return value as T
    }
}

fun <T> later(block: () -> T) = Later(block)

val uriMatcher by later {

}

































