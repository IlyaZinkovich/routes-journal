package com.routes.journal.gateway;

import com.routes.journal.entitity.Route;

import java.util.List;

public interface RouteGateway {

    void saveRoutes(List<Route> routesBatch);
}
