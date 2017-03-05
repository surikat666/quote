package com.strokova.quote.service.model;

import lombok.Getter;

import java.util.List;

import static java.util.Collections.singletonList;

@Getter
public class DataEvolution {

    private final List<String> labels;
    private final List<Series> series;

    public DataEvolution(List<String> labels, List<Long> series) {
        this.labels = labels;
        this.series = singletonList(new Series(series));
    }

    @Getter
    public static class Series {

        private final List<Long> data;

        private Series(List<Long> data) {
            this.data = data;
        }
    }
}
