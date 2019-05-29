package com.github.simkuenzi.cronext

import java.time.LocalDateTime

interface CronField {
    fun matches(dateTime: LocalDateTime): Boolean
}