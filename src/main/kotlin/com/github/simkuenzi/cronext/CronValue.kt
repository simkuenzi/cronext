package com.github.simkuenzi.cronext

import java.util.stream.IntStream

//
//  Minute 	Stunde 	Tag des Monats 	Monat 	Wochentag 	Bedeutung
//  * 	* 	* 	* 	* 	Jede Minute, rund um die Uhr, sieben Tage die Woche
//  */5 	* 	* 	* 	* 	Alle 5 Minuten
//  0 	0 	* 	* 	* 	Täglich null Uhr
//  0 	1 	1-7 	12 	1 	Das Programm wird um 1:00 an jeden Tag zwischen 1. bis 7. Dezember UND zusätzlich an jeden Montag im Dezember aufgerufen, da hier der Sonderfall greift, dass nur entweder der Tag des Monats oder der Tag der Woche übereinstimmen muss (siehe oben).
//  1-59/2 	* 	* 	* 	* 	Jede ungerade Minute
//  20,30 	1 	* 	* 	1-5 	Montags bis Freitags jeweils um 01:20 und 01:30 Uhr
//  5 	* 	* 	* 	* 	Fünf Minuten nach jeder vollen Stunde
//  5-59/20 	* 	* 	* 	* 	5, 25 und 45 Minuten nach jeder vollen Stunde
//  59 	23 	* 	* 	0 	Jeden Sonntag um 23:59 Uhr. Manche Cron-Syntax erlaubt neben 0 für Sonntag auch 7 für Sonntag.
//

/*
    simple = n,n2,n3|*
    range = n-m
    time = simple|range
    interval = time/i

 */

class CronValue(private val valueExpr: String) {
    fun matches(value: Int, range: IntRange) = valueExpr == "?" || matchingValues(range).anyMatch { it == value }

    private fun matchingValues(range: IntRange): IntStream {
        val intervalExpr = intervalExpr()
        if (intervalExpr is String) {
            val interval = intervalExpr.toInt()
            val first = times(range).findFirst().orElse(0)
            return timesForInterval(range).filter {(it - first) % interval == 0}
        } else {
            return times(range)
        }
    }

    private fun times(range: IntRange) = if (timeExpr().contains('-')) range() else simple(range)

    private fun timesForInterval(range: IntRange) = if (timeExpr().contains('-')) range() else range.asStream()

    private fun timeExpr() = valueExpr.split("/")[0]

    private fun intervalExpr(): String? {
        val split = valueExpr.split("/")
        return if (split.size > 1) split[1] else null
    }

    private fun simple(range: IntRange) = if (timeExpr() == "*") range.asStream() else timeExpr().split(",").stream().mapToInt { it.toInt() }

    private fun range(): IntStream {
        val split = timeExpr().split("-")
        return IntStream.range(split[0].toInt(), split[1].toInt() + 1)
    }

}

fun IntRange.asStream(): IntStream = IntStream.range(start, endInclusive + 1)