package com.routes.journal.client;

import com.routes.journal.entitity.Route;

import java.util.List;

public class RoutesBatch {

    private List<Route> routes;

    public RoutesBatch(List<Route> routes) {
        this.routes = routes;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
