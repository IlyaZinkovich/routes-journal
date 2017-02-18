package com.routes.journal.boundary;

import com.routes.journal.entitity.Route;

import java.time.LocalDate;
import java.util.List;

public class SaveRoutesTask {

    private LocalDate updateDate;
    private List<Route> routes;

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
