package com.github.simkuenzi.cronext;

import java.time.LocalDateTime;

interface CronField {
    boolean matches(LocalDateTime dateTime);
}