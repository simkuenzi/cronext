package com.github.simkuenzi.cronext

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CronValueTest {

    @Test
    fun simple() {
        val cronValue = CronValue("4")
        assertTrue(cronValue.matches(4, 0..59))
        assertFalse(cronValue.matches(5, 0..59))
    }

    @Test
    fun all() {
        val cronValue = CronValue("*")
        (0..59).forEach {
            assertTrue(cronValue.matches(it, 0..59), String.format("%d should match.", it))
        }
    }

    @Test
    fun list() {
        val cronValue = CronValue("4,6,27")
        assertTrue(cronValue.matches(4, 0..59))
        assertTrue(cronValue.matches(6, 0..59))
        assertTrue(cronValue.matches(27, 0..59))
        assertFalse(cronValue.matches(5, 0..59))
    }

    @Test
    fun range() {
        val cronValue = CronValue("43-46")
        assertTrue(cronValue.matches(43, 0..59))
        assertTrue(cronValue.matches(45, 0..59))
        assertTrue(cronValue.matches(46, 0..59))
        assertFalse(cronValue.matches(42, 0..59))
        assertFalse(cronValue.matches(47, 0..59))
    }

    @Test
    fun interval() {
        val cronValue = CronValue("16/15")
        assertTrue(cronValue.matches(16, 0..59))
        assertTrue(cronValue.matches(31, 0..59))
        assertTrue(cronValue.matches(46, 0..59))
        assertTrue(cronValue.matches(1, 0..59))
        assertFalse(cronValue.matches(47, 0..59))
        assertFalse(cronValue.matches(15, 0..59))
    }

    @Test
    fun intervalRange() {
        val cronValue = CronValue("16-31/15")
        assertTrue(cronValue.matches(16, 0..59))
        assertTrue(cronValue.matches(31, 0..59))
        assertFalse(cronValue.matches(46, 0..59))
        assertFalse(cronValue.matches(1, 0..59))
        assertFalse(cronValue.matches(47, 0..59))
        assertFalse(cronValue.matches(15, 0..59))
    }
}