package com.routes.journal.entitity;

import java.time.LocalDate;
import java.util.stream.Stream;

import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.joining;

public class Update {

    private String city;
    private String country;
    private LocalDate date;
    private String source;

    public Update(String city, String country, LocalDate date, String source) {
        this.city = city;
        this.country = country;
        this.date = date;
        this.source = source;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return Stream.of(city, country, date.format(ofPattern("yyyy-MM-dd")), source).collect(joining(":"));
    }
}
