package com.hankadler.util

class App {
    fun main() {
        println(CallStack)
        println(CallStack.getCallerOf(this::class.qualifiedName))
    }
}

fun main() {
    App().main()
}
