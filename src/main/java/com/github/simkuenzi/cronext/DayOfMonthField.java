package com.github.simkuenzi.cronext;

import java.time.LocalDateTime;

class DayOfMonthField implements CronField {
    private CronValue value;

    DayOfMonthField(CronValue value) {
        this.value = value;
    }

    @Override
    public boolean matches(LocalDateTime dateTime) {
        return value.matches(dateTime.getDayOfMonth(), new Range(1, 31));
    }
}
