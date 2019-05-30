package com.github.simkuenzi.cronext;

import java.time.LocalDateTime;

class MinuteField implements CronField {
    private CronValue value;

    MinuteField(CronValue value) {
        this.value = value;
    }

    @Override
    public boolean matches(LocalDateTime dateTime) {
        return value.matches(dateTime.getMinute(), new Range(0, 59));
    }
}
