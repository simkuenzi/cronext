package com.github.simkuenzi.cronext

import java.time.LocalDateTime

class MinuteField(private val value: CronValue) : CronField {
    override fun matches(dateTime: LocalDateTime) = value.matches(dateTime.minute, 0..59)
}