package com.github.simkuenzi.cronext;

import java.time.LocalDateTime;

public interface CronField {
    boolean matches(LocalDateTime dateTime);
}