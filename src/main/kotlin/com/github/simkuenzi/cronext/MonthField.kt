package com.github.simkuenzi.cronext

import java.time.LocalDateTime

class MonthField(private val value: CronValue) : CronField {
    override fun matches(dateTime: LocalDateTime) = value.matches(dateTime.month.value, 1..12)
}