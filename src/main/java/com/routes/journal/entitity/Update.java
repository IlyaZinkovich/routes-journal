package com.routes.journal.entitity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        return city == null ? (city + ":") : "" + country + ":"
                + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ":" + source;
    }
}
