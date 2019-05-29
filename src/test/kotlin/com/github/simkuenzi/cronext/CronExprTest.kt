package com.github.simkuenzi.cronext

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class CronExprTest {

    @Test
    fun next() {
        val cronExpr = CronExpr("4 19 2 12 ?")
        val next = cronExpr.next(LocalDateTime.of(2019, 2, 8, 17, 43))
        assertEquals(LocalDateTime.of(2019, 12, 2, 19, 4), next)
    }
}