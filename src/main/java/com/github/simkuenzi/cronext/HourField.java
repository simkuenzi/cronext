package com.github.simkuenzi.cronext;

import java.time.LocalDateTime;

class HourField implements CronField {
    private CronValue value;

    HourField(CronValue value) {
        this.value = value;
    }

    @Override
    public boolean matches(LocalDateTime dateTime) {
        return value.matches(dateTime.getHour(), new Range(0, 23));
    }
}
