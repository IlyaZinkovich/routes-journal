package com.routes.journal.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.routes.journal.boundary.FindRoutesTask;
import com.routes.journal.boundary.SaveRoutesTask;
import com.routes.journal.interactor.UpdateInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zeromq.ZMQ.Socket;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;

import static java.lang.Thread.currentThread;

@Controller
public class UpdateController {

    @Autowired
    @Qualifier("saveRoutesTaskSubscriber")
    private Socket subscriber;

    @Autowired
    @Qualifier("findRoutesTaskPublisher")
    private Socket publisher;

    @Value("${zeromq.topic.findRoutes.envelope}")
    private String findRoutesTaskEnvelope;

    @Autowired
    private UpdateInteractor updateInteractor;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(path = "/routes/{date}")
    public void findRoutes(@PathVariable(name = "date") LocalDate date,
                           @RequestParam(name = "destination") String destination) {
        publisher.sendMore(findRoutesTaskEnvelope);
        sendTask(new FindRoutesTask(date, destination));
    }

    @PostConstruct
    public void subscribe() {
        new Thread(this::processIncomingMessages).start();
    }

    private void processIncomingMessages() {
        while (!currentThread().isInterrupted()) {
            String taskType = subscriber.recvStr();
            String taskContents = subscriber.recvStr();
            SaveRoutesTask saveRoutesTask = convert(taskContents);
            updateInteractor.updateRoutes(saveRoutesTask.getRoutes(), saveRoutesTask.getUpdateDate());
        }
    }

    private void sendTask(FindRoutesTask findRoutesTask) {
        try {
            publisher.send(objectMapper.writeValueAsString(findRoutesTask));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private SaveRoutesTask convert(String contents) {
        try {
            return objectMapper.readValue(contents, SaveRoutesTask.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
