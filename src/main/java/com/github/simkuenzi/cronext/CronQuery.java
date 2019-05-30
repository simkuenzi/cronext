package com.github.simkuenzi.cronext;

import java.time.LocalDateTime;

@SuppressWarnings("WeakerAccess")
public class CronQuery {

    private LocalDateTime dateTime;
    private CronExpr cronExpr;

    CronQuery(LocalDateTime dateTime, CronExpr cronExpr) {
        this.dateTime = dateTime;
        this.cronExpr = cronExpr;
    }

    public LocalDateTime next() {
        return cronExpr.next(dateTime);
    }

    public CronQuery skip() {
      return cronExpr.skip(dateTime);
    }
}