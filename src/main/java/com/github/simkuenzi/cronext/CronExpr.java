package com.github.simkuenzi.cronext;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/*
┌───────────── Minute (0 - 59)
│ ┌───────────── Stunde (0 - 23)
│ │ ┌───────────── Tag des Monats (1 - 31)
│ │ │ ┌───────────── Monat (1 - 12)
│ │ │ │ ┌───────────── Wochentag (0 - 6)
│ │ │ │ │
* * * * *
 */
@SuppressWarnings("WeakerAccess")
public class CronExpr {
    private String expression;

    public CronExpr(String expression) {
        this.expression = expression;
    }

    public LocalDateTime next(LocalDateTime now) {
        List<CronValue> fieldExpressions = Stream.of(expression.trim().split("\\s+"))
                .map(CronValue::new)
                .collect(Collectors.toList());

        List<CronField> fields = IntStream.range(0, fieldExpressions.size()).mapToObj(i -> {
            switch (i) {
                case 0:
                    return new MinuteField(fieldExpressions.get(i));
                case 1:
                    return new HourField(fieldExpressions.get(i));
                case 2:
                    return new DayOfMonthField(fieldExpressions.get(i));
                case 3:
                    return new MonthField(fieldExpressions.get(i));
                case 4:
                    return new DayOfWeekField(fieldExpressions.get(i));
                default:
                    return new UnknownField();
            }
        }).collect(Collectors.toList());


        return candidates(now)
                .filter(dateTime -> fields.stream().allMatch(f -> f.matches(dateTime)))
                .findFirst()
                .orElse(LocalDateTime.MAX);
    }

    public CronQuery skip(LocalDateTime now, int times) {
        CronQuery query = skip(now);
        for (int i = 1; i < times; i++) {
            query = query.skip();
        }
        return query;
    }


    public CronQuery skip(LocalDateTime now) {
        return new CronQuery(next(now), this);
    }

    private Stream<LocalDateTime> candidates(LocalDateTime now) {
        LocalDateTime first = now.withSecond(0).withNano(0);
        LocalDateTime last = LocalDateTime.of(first.toLocalDate().plus(Period.ofYears(1)), first.toLocalTime());
        Duration duration = Duration.between(first, last);
        return LongStream.range(1, duration.toMinutes() + 1).mapToObj(m -> first.plus(Duration.ofMinutes(m)));
    }
}