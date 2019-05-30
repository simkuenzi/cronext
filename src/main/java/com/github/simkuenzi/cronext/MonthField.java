package com.github.simkuenzi.cronext;

import java.time.LocalDateTime;

class MonthField implements CronField {
    private CronValue value;

    MonthField(CronValue value) {
        this.value = value;
    }

    @Override
    public boolean matches(LocalDateTime dateTime) {
        return value.matches(dateTime.getMonth().getValue(), new Range(1, 12));
    }
}
