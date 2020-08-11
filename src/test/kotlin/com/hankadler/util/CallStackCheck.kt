/*
 * Copyright (C) Hank Adler 2020
 */

package com.hankadler.util

/**
 * `CallStack` "quick-n-dirty" checks.
 */
class CallStackCheck {
    class SubClass {
        fun checkAll() {
            println("=== checkAll() ===")
            println("--- Callable Units ---")
            println(CallStack)

            println("--- Class Names Stack ---")
            CallStack.printClassNames()

            println("\n--- Caller of Sub Class ---")
            println(CallStack.getCallerOf(Thread.currentThread().stackTrace[1]))

            println("\n--- Caller Class Name of Sub Class ---")
            println(CallStack.getCallerOf(this::class.qualifiedName))

            println("\n--- 'com.hankadler.util.CallStackCheckKt' Units ---")
            CallStack.getUnits(CallStack.classNames[0]).forEach { println(it) }
        }
    }
}

/**
 * Runs "quick-n-dirty" checks.
 */
fun main() {
    CallStackCheck.SubClass().checkAll()
}
