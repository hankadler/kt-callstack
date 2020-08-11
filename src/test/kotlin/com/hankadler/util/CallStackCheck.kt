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

            println("\n--- ClassNames Stack ---")
            CallStack.printClassNames()

            println("\n--- Caller of SubClass ---")
            println(CallStack.getCallerOf(Thread.currentThread().stackTrace[1]))

            println("\n--- Caller ClassName of SubClass ---")
            println(CallStack.getCallerOf(this::class.qualifiedName))
        }
    }
}

/**
 * Runs "quick-n-dirty" checks.
 */
fun main() {
    CallStackCheck.SubClass().checkAll()
}
