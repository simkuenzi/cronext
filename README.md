# croNext

croNext evaluates the next schedule time for a specified cron expression.

``` java
CronExpr cronExpr = new CronExpr("4 19 2 12 ?");
LocalDateTime nextScheduleTime = cronExpr.next(LocalDateTime.of(2019, 2, 8, 17, 43));
```