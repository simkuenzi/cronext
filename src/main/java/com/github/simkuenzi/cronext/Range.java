package com.github.simkuenzi.cronext;

import java.util.stream.IntStream;

class Range {
    private int first;
    private int last;

    Range(int first, int last) {
        this.first = first;
        this.last = last;
    }

    IntStream stream() {
        return IntStream.range(first, last + 1);
    }
}
