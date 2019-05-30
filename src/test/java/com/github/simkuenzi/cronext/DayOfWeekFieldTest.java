package com.github.simkuenzi.cronext;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DayOfWeekFieldTest {
    @Test
    void star() {
        assertTrue(new DayOfWeekField(new CronValue("*")).matches(LocalDateTime.of(2019, 2, 10, 2, 0)));
    }

}