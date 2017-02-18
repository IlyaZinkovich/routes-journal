package com.routes.journal.gateway;

import com.routes.journal.entitity.Update;

public interface UpdateGateway {

    void save(Update update);

    boolean exists(Update update);
}
