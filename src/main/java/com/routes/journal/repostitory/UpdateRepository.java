package com.routes.journal.repostitory;

import com.routes.journal.entitity.Update;
import com.routes.journal.gateway.UpdateGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;

@Repository
public class UpdateRepository implements UpdateGateway {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private Integer port;

    private Jedis jedis;

    @PostConstruct
    public void getJedis() {
        this.jedis = new Jedis(host, port);
    }

    @Override
    public void save(Update update) {
        jedis.setnx(update.toString(), "incoming");
    }

    @Override
    public boolean exists(Update update) {
        return jedis.exists(update.toString());
    }
}