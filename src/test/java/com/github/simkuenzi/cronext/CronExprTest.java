package com.github.simkuenzi.cronext;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CronExprTest {

    @Test
    void next1() {
        CronExpr cronExpr = new CronExpr("4 19 2 12 ?");
        LocalDateTime start = LocalDateTime.of(2019, 2, 8, 17, 43);
        assertEquals(LocalDateTime.of(2019, 12, 2, 19, 4), cronExpr.next(start));
        assertEquals(LocalDateTime.of(2020, 12, 2, 19, 4), cronExpr.skip(start).next());
        assertEquals(LocalDateTime.of(2021, 12, 2, 19, 4), cronExpr.skip(start, 2).next());
        assertEquals(LocalDateTime.of(2022, 12, 2, 19, 4), cronExpr.skip(start, 3).next());
    }

    @Test
    void next2() {
        CronExpr cronExpr = new CronExpr("0 2 * * *");
        LocalDateTime start = LocalDateTime.of(2019, 2, 8, 17, 43);
        assertEquals(LocalDateTime.of(2019, 2, 9, 2, 0), cronExpr.next(start));
        assertEquals(LocalDateTime.of(2019, 2, 10, 2, 0), cronExpr.skip(start).next());
        assertEquals(LocalDateTime.of(2019, 2, 11, 2, 0), cronExpr.skip(start, 2).next());
        assertEquals(LocalDateTime.of(2019, 2, 12, 2, 0), cronExpr.skip(start, 3).next());
    }

    @Test
    void next3() {
        CronExpr cronExpr = new CronExpr("0 5,17 * * *");
        LocalDateTime start = LocalDateTime.of(2019, 2, 8, 17, 43);
        assertEquals(LocalDateTime.of(2019, 2, 9, 5, 0), cronExpr.next(start));
        assertEquals(LocalDateTime.of(2019, 2, 9, 17, 0), cronExpr.skip(start).next());
        assertEquals(LocalDateTime.of(2019, 2, 10, 5, 0), cronExpr.skip(start, 2).next());
        assertEquals(LocalDateTime.of(2019, 2, 10, 17, 0), cronExpr.skip(start, 3).next());
    }

    @Test
    void next4() {
        CronExpr cronExpr = new CronExpr("* * * * *");
        LocalDateTime start = LocalDateTime.of(2019, 2, 8, 17, 43);
        assertEquals(LocalDateTime.of(2019, 2, 8, 17, 44), cronExpr.next(start));
        assertEquals(LocalDateTime.of(2019, 2, 8, 17, 45), cronExpr.skip(start).next());
        assertEquals(LocalDateTime.of(2019, 2, 8, 17, 46), cronExpr.skip(start, 2).next());
        assertEquals(LocalDateTime.of(2019, 2, 8, 17, 47), cronExpr.skip(start, 3).next());
    }

    @Test
    void next5() {
        CronExpr cronExpr = new CronExpr("0 17 * * sun");
        LocalDateTime start = LocalDateTime.of(2019, 2, 8, 17, 43);
        assertEquals(LocalDateTime.of(2019, 2, 10, 17, 0), cronExpr.next(start));
        assertEquals(LocalDateTime.of(2019, 2, 17, 17, 0), cronExpr.skip(start).next());
        assertEquals(LocalDateTime.of(2019, 2, 24, 17, 0), cronExpr.skip(start, 2).next());
        assertEquals(LocalDateTime.of(2019, 3, 3, 17, 0), cronExpr.skip(start, 3).next());
    }

    @Test
    void next6() {
        CronExpr cronExpr = new CronExpr("*/10 * * * *");
        LocalDateTime start = LocalDateTime.of(2019, 2, 8, 17, 43);
        assertEquals(LocalDateTime.of(2019, 2, 8, 17, 50), cronExpr.next(start));
        assertEquals(LocalDateTime.of(2019, 2, 8, 18, 0), cronExpr.skip(start).next());
        assertEquals(LocalDateTime.of(2019, 2, 8, 18, 10), cronExpr.skip(start, 2).next());
        assertEquals(LocalDateTime.of(2019, 2, 8, 18, 20), cronExpr.skip(start, 3).next());
    }

    @Test
    void next7() {
        CronExpr cronExpr = new CronExpr("0 0 6 jan,may,aug *");
        LocalDateTime start = LocalDateTime.of(2019, 2, 8, 17, 43);
        assertEquals(LocalDateTime.of(2019, 5, 6, 0, 0), cronExpr.next(start));
        assertEquals(LocalDateTime.of(2019, 8, 6, 0, 0), cronExpr.skip(start).next());
        assertEquals(LocalDateTime.of(2020, 1, 6, 0, 0), cronExpr.skip(start, 2).next());
        assertEquals(LocalDateTime.of(2020, 5, 6, 0, 0), cronExpr.skip(start, 3).next());
    }

    @Test
    void next8() {
        CronExpr cronExpr = new CronExpr("0 17 * * sun,fri");
        LocalDateTime start = LocalDateTime.of(2019, 2, 8, 17, 43);
        assertEquals(LocalDateTime.of(2019, 2, 10, 17, 0), cronExpr.next(start));
        assertEquals(LocalDateTime.of(2019, 2, 15, 17, 0), cronExpr.skip(start).next());
        assertEquals(LocalDateTime.of(2019, 2, 17, 17, 0), cronExpr.skip(start, 2).next());
        assertEquals(LocalDateTime.of(2019, 2, 22, 17, 0), cronExpr.skip(start, 3).next());
    }

    @Test
    void next9() {
        CronExpr cronExpr = new CronExpr("0 */4 * * *");
        LocalDateTime start = LocalDateTime.of(2019, 2, 8, 17, 43);
        assertEquals(LocalDateTime.of(2019, 2, 8, 20, 0), cronExpr.next(start));
        assertEquals(LocalDateTime.of(2019, 2, 9, 0, 0), cronExpr.skip(start).next());
        assertEquals(LocalDateTime.of(2019, 2, 9, 4, 0), cronExpr.skip(start, 2).next());
        assertEquals(LocalDateTime.of(2019, 2, 9, 8, 0), cronExpr.skip(start, 3).next());
    }

    @Test
    void next10() {
        CronExpr cronExpr = new CronExpr("0 4,17 * * sun,mon");
        LocalDateTime start = LocalDateTime.of(2019, 2, 8, 17, 43);
        assertEquals(LocalDateTime.of(2019, 2, 10, 4, 0), cronExpr.next(start));
        assertEquals(LocalDateTime.of(2019, 2, 10, 17, 0), cronExpr.skip(start).next());
        assertEquals(LocalDateTime.of(2019, 2, 11, 4, 0), cronExpr.skip(start, 2).next());
        assertEquals(LocalDateTime.of(2019, 2, 11, 17, 0), cronExpr.skip(start, 3).next());
    }

    @Test
    void whitespace() {
        CronExpr cronExpr = new CronExpr("  4  19 2    12  ?    ");
        LocalDateTime start = LocalDateTime.of(2019, 2, 8, 17, 43);
        assertEquals(LocalDateTime.of(2019, 12, 2, 19, 4), cronExpr.next(start));
        assertEquals(LocalDateTime.of(2020, 12, 2, 19, 4), cronExpr.skip(start).next());
        assertEquals(LocalDateTime.of(2021, 12, 2, 19, 4), cronExpr.skip(start, 2).next());
        assertEquals(LocalDateTime.of(2022, 12, 2, 19, 4), cronExpr.skip(start, 3).next());
    }
}