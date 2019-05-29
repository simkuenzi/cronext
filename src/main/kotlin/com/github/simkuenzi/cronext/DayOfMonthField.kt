package com.github.simkuenzi.cronext

import java.time.LocalDateTime

class DayOfMonthField(private val value: CronValue) : CronField {
    override fun matches(dateTime: LocalDateTime) = value.matches(dateTime.dayOfMonth, 0..31)
}