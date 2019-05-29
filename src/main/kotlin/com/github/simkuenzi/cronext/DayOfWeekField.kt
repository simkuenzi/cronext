package com.github.simkuenzi.cronext

import java.time.LocalDateTime

class DayOfWeekField(private val value: CronValue) : CronField {
    override fun matches(dateTime: LocalDateTime) = value.matches(dateTime.dayOfWeek.value - 1, 0..6)
}