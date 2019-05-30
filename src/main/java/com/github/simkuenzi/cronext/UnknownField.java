package com.github.simkuenzi.cronext;

import java.time.LocalDateTime;

class UnknownField implements CronField {
    @Override
    public boolean matches(LocalDateTime dateTime) {
        return true;
    }
}
