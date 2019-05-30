package com.github.simkuenzi.cronext;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CronValueTest {

    @Test
    void simple() {
        CronValue cronValue = new CronValue("4");
        assertTrue(cronValue.matches(4, new Range(0, 59)));
        assertFalse(cronValue.matches(5, new Range(0, 59)));
    }

    @Test
    void all() {
        CronValue cronValue = new CronValue("*");
        new Range(0, 59).stream().forEach(x -> {
            assertTrue(cronValue.matches(x, new Range(0, 59)), String.format("%d should match.", x));
        });
    }

    @Test
    void list() {
        CronValue cronValue = new CronValue("4,6,27");
        assertTrue(cronValue.matches(4, new Range(0, 59)));
        assertTrue(cronValue.matches(6, new Range(0, 59)));
        assertTrue(cronValue.matches(27, new Range(0, 59)));
        assertFalse(cronValue.matches(5, new Range(0, 59)));
    }

    @Test
    void range() {
        CronValue cronValue = new CronValue("43-46");
        assertTrue(cronValue.matches(43, new Range(0, 59)));
        assertTrue(cronValue.matches(45, new Range(0, 59)));
        assertTrue(cronValue.matches(46, new Range(0, 59)));
        assertFalse(cronValue.matches(42, new Range(0, 59)));
        assertFalse(cronValue.matches(47, new Range(0, 59)));
    }

    @Test
    void interval() {
        CronValue cronValue = new CronValue("16/15");
        assertTrue(cronValue.matches(16, new Range(0, 59)));
        assertTrue(cronValue.matches(31, new Range(0, 59)));
        assertTrue(cronValue.matches(46, new Range(0, 59)));
        assertTrue(cronValue.matches(1, new Range(0, 59)));
        assertFalse(cronValue.matches(47, new Range(0, 59)));
        assertFalse(cronValue.matches(15, new Range(0, 59)));
    }

    @Test
    void intervalRange() {
        CronValue cronValue = new CronValue("16-31/15");
        assertTrue(cronValue.matches(16, new Range(0, 59)));
        assertTrue(cronValue.matches(31, new Range(0, 59)));
        assertFalse(cronValue.matches(46, new Range(0, 59)));
        assertFalse(cronValue.matches(1, new Range(0, 59)));
        assertFalse(cronValue.matches(47, new Range(0, 59)));
        assertFalse(cronValue.matches(15, new Range(0, 59)));
    }
}
