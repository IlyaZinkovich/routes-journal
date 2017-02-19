package com.routes.journal.interactor;

import com.routes.journal.entitity.Route;
import com.routes.journal.entitity.Update;
import com.routes.journal.gateway.RouteGateway;
import com.routes.journal.gateway.UpdateGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UpdateInteractor {

    @Autowired
    private UpdateGateway updateGateway;

    @Autowired
    private RouteGateway routeGateway;

    public void updateRoutes(List<Route> routes, LocalDate updateDate) {
        routes.stream().findFirst().map(route ->
                new Update(route.getTo().getCity(), route.getTo().getCountry(), updateDate, route.getSource()))
                .filter(update -> !exists(update))
                .ifPresent(update -> save(update, routes));
    }

    private boolean exists(Update update) {
        return updateGateway.exists(update);
    }

    private void save(Update update, List<Route> routes) {
        routeGateway.saveRoutes(routes);
        updateGateway.save(update);
    }

    public boolean routesUpdated(LocalDate date, String city, String country) {
        return exists(new Update(city, country, date, "Flickr"));
    }
}
