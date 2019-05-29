package com.github.simkuenzi.cronext

import java.time.Duration
import java.time.LocalDateTime
import java.time.Period
import java.util.stream.LongStream
import java.util.stream.Stream

/*
┌───────────── Minute (0 - 59)
│ ┌───────────── Stunde (0 - 23)
│ │ ┌───────────── Tag des Monats (1 - 31)
│ │ │ ┌───────────── Monat (1 - 12)
│ │ │ │ ┌───────────── Wochentag (0 - 6)
│ │ │ │ │
* * * * *
 */
class CronExpr(private val expression: String) {
    fun next(now: LocalDateTime): LocalDateTime {
        val fields = expression.trim().split(Regex("""\s+""")).withIndex().map {
            when (it.index) {
                0 -> MinuteField(CronValue(it.value))
                1 -> HourField(CronValue(it.value))
                2 -> DayOfMonthField(CronValue(it.value))
                3 -> MonthField(CronValue(it.value))
                4 -> DayOfWeekField(CronValue(it.value))
                else -> UnknownField()
            }
        }

        return candidates(now)
            .filter {dateTime -> fields.stream().allMatch { it.matches(dateTime) } }
            .findFirst()
            .orElseGet { LocalDateTime.MAX }
    }

    fun skip(now: LocalDateTime, times: Int) : CronQuery {
        var query = skip(now)
        for (x in (2..times)) {
            query = query.skip()
        }
        return query
    }

    fun skip(now: LocalDateTime) = CronQuery(next(now), this)

    private fun candidates(now: LocalDateTime) : Stream<LocalDateTime> {
        val first = now.withSecond(0).withNano(0)
        val last = LocalDateTime.of(first.toLocalDate() + Period.ofYears(1), first.toLocalTime())
        val duration = Duration.between(first, last)
        return LongStream.range(1, duration.toMinutes()).mapToObj { first + Duration.ofMinutes(it) }
    }
}