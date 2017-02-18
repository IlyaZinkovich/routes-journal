package com.routes.journal.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zeromq.ZMQ.*;

import static org.zeromq.ZMQ.*;

@Configuration
public class WebConfig {

    @Value("${zeromq.topic.saveRoutes.address}")
    private String saveRoutesTasksTopicAddress;

    @Value("${zeromq.topic.saveRoutes.ioThreads}")
    private int saveRoutesTasksTopicIoThreads;

    @Value("${zeromq.topic.saveRoutes.envelope}")
    private String saveRoutesTaskEnvelope;

    @Value("${zeromq.topic.findRoutes.address}")
    private String findRoutesTasksTopicAddress;

    @Value("${zeromq.topic.findRoutes.ioThreads}")
    private int findRoutesTasksTopicIoThreads;

    @Bean(destroyMethod = "term")
    public Context findRoutesTasksTopicContext() {
        return context(saveRoutesTasksTopicIoThreads);
    }

    @Bean(destroyMethod = "close", name = "findRoutesTaskPublisher")
    public Socket findRoutesTaskPublisher() {
        Socket socket = findRoutesTasksTopicContext().socket(PUB);
        socket.bind(findRoutesTasksTopicAddress);
        return socket;
    }

    @Bean(destroyMethod = "term")
    public Context saveRoutesTasksTopicContext() {
        return context(saveRoutesTasksTopicIoThreads);
    }

    @Bean(destroyMethod = "close", name = "saveRoutesTaskSubscriber")
    public Socket saveRoutesTaskSubscriber() {
        Socket subscriber = saveRoutesTasksTopicContext().socket(SUB);
        subscriber.connect(saveRoutesTasksTopicAddress);
        subscriber.subscribe(saveRoutesTaskEnvelope.getBytes());
        return subscriber;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }
}
