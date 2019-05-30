package com.github.simkuenzi.cronext;

import java.time.LocalDateTime;

class DayOfWeekField implements CronField {
    private CronValue value;

    DayOfWeekField(CronValue value) {
        this.value = value;
    }

    @Override
    public boolean matches(LocalDateTime dateTime) {
        return value.matches(dateTime.getDayOfWeek().getValue() - 1, new Range(0, 6));
    }
}
