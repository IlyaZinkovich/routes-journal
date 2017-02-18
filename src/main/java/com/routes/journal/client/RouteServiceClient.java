package com.routes.journal.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.routes.journal.entitity.Route;
import com.routes.journal.gateway.RouteGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

import java.util.List;

import static java.util.Collections.singletonList;

@Service
public class RouteServiceClient implements RouteGateway {

    @Value("${routes.service.url}")
    private String routesServiceUrl;

    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    private void getRestTemplate() {
        this.restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(objectMapper);
        restTemplate.setMessageConverters(singletonList(jsonMessageConverter));
    }

    @Override
    public void saveRoutes(List<Route> routesBatch) {
        restTemplate.postForObject(routesServiceUrl + "/routes/batch", new RoutesBatch(routesBatch), String.class);
    }
}
