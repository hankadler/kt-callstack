/*
 * Copyright (C) Hank Adler 2020
 */

package com.hankadler.util

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * `CallStack` junit tests.
 *
 * @author   hank@hankadler.com
 * @version  0.1.0
 * @license  MIT
 */
class CallStackTest {
    private var test = ""
    private var description = ""

    /**
     * Prints `test` and `description` after each Test.
     */
    @AfterEach
    fun tearDown() {
        println("\n*** $test ***")
        println("$description\n")
    }

    /**
     * Asserts `units` returns correct number of execution points.
     */
    @Test
    fun get() {
        test = "`units` property"
        description = "Asserts `units` returns correct number of execution points."

        assertEquals(Thread.currentThread().stackTrace.size + 1, CallStack.units.size)
    }
}

fun main() {
    CallStackTest()
}
