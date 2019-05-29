package com.github.simkuenzi.cronext

import java.time.LocalDateTime

class UnknownField : CronField {
    override fun matches(dateTime: LocalDateTime) = true
}