package com.github.simkuenzi.cronext

import java.time.LocalDateTime

class CronQuery(val dateTime: LocalDateTime, val cronExpr: CronExpr) {
    fun next() = cronExpr.next(dateTime)
    fun skip() = cronExpr.skip(dateTime)
}