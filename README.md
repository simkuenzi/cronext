# croNext

croNext evaluates the next schedule time for a specified cron expression.

``` java
CronExpr cronExpr = new CronExpr("4 19 2 12 ?");
LocalDateTime nextScheduleTime = cronExpr.next(LocalDateTime.of(2019, 2, 8, 17, 43));
System.out.println(nextScheduleTime);
```
The example writes the next schedule time after February 8th 2019, 17:43 to the console.
```
2019-12-02T19:04
```