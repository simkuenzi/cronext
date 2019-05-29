# croNext

croNext evaluates the next schedule time for a specified cron expression.

``` kotlin
val cronExpr = CronExpr("4 19 2 12 ?")
val nextScheduleTime = cronExpr.next(LocalDateTime.of(2019, 2, 8, 17, 43))
```