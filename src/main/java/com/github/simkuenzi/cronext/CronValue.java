package com.github.simkuenzi.cronext;

import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;
import java.util.stream.Stream;

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

class CronValue {
    private String valueExpr;

    CronValue(String valueExpr) {
        this.valueExpr = valueExpr;
    }


    public boolean matches(int value, Range range) {
        return valueExpr.equals("?") || matchingValues(range).anyMatch(x -> x == value);
    }

    private IntStream matchingValues(Range range) {
        String intervalExpr = intervalExpr();
        if (intervalExpr != null) {
            int interval = Integer.parseInt(intervalExpr);
            int first = times(range).findFirst().orElse(0);
            return timesForInterval(range).filter(t -> (t - first) % interval == 0);
        } else {
            return times(range);
        }
    }

    private IntStream times(Range range) {
        return timeExpr().contains("-") ? range() : simple(range);
    }

    private IntStream timesForInterval(Range range) {
        return timeExpr().contains("-") ? range() : range.stream();
    }

    private String timeExpr() {
        return valueExpr.split("/")[0];
    }

    // Nullable
    private String intervalExpr() {
        String[] split = valueExpr.split("/");
        return split.length > 1 ? split[1] : null;
    }

    private IntStream simple(Range range) {
        return timeExpr().equals("*") ? range.stream() : Stream.of(timeExpr().split(",")).mapToInt(this::parseScalar);
    }

    private IntStream range() {
        String[] split = timeExpr().split("-");
        return IntStream.range(parseScalar(split[0]), parseScalar(split[1]) + 1);
    }

    private int parseScalar(String scalarExpr) {
        switch (scalarExpr) {
            case "mon":
                return 0;
            case "jan":
            case "tue":
                return 1;
            case "feb":
            case "wed":
                return 2;
            case "mar":
            case "thu":
                return 3;
            case "apr":
            case "fri":
                return 4;
            case "may":
            case "sat":
                return 5;
            case "jun":
            case "sun":
                return 6;
            case "jul":
                return 7;
            case "aug":
                return 8;
            case "sep":
                return 9;
            case "oct":
                return 10;
            case "nov":
                return 11;
            case "dec":
                return 12;
            default:
                return Integer.parseInt(scalarExpr);
        }
    }
}
