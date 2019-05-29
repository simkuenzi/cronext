package com.github.simkuenzi.cronext

import java.time.LocalDateTime

class HourField(private val value: CronValue) : CronField {
    override fun matches(dateTime: LocalDateTime) = value.matches(dateTime.hour, 0..23)
}