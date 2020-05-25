package dev.abgeo.bugsnitch.service;

import dev.abgeo.bugsnitch.model.Bug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Service for working with Web Socket
 *
 * @author Temuri Takalandze
 */
@Service
public class WebSocketService {

    /**
     * SimpMessagingTemplate dependency.
     */
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Publish new Bug entity.
     *
     * @param bug New Bug entity
     */
    public void bugCreated(Bug bug) {
        messagingTemplate.convertAndSend("/topic/bug/new", bug);
    }

    /**
     * Publish updated Bug entity.
     *
     * @param bug Updated Bug entity
     */
    public void bugUpdated(Bug bug) {
        System.out.println("bugUpdated");
        messagingTemplate.convertAndSend("/topic/bug/" + bug.getId(), bug);
    }

}
