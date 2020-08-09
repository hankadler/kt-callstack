/*
 * Copyright (C) Hank Adler 2020
 */

package com.hankadler.util

/**
 * `CallStack` "quick-n-dirty" checks.
 *
 * @author   hank@hankadler.com
 * @version  0.1.0
 * @license  MIT
 */
class CallStackCheck {
    class SubClass {
        fun checkAll() {
            println("*** Callable Units ***")
            println(CallStack)

            println("\n*** ClassNames Stack ***")
            CallStack.printClassNames()

            println("\n*** Caller of SubClass ***")
            println(CallStack.getCallerOf(this::class.qualifiedName))
        }
    }
}

fun main() {
    CallStackCheck.SubClass().checkAll()
}
