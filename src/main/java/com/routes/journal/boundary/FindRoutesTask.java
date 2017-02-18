package com.routes.journal.boundary;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public class FindRoutesTask {

    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String destination;

    public FindRoutesTask() {
    }

    public FindRoutesTask(LocalDate date, String destination) {
        this.date = date;
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
