package dev.abgeo.bugsnitch.service;

import dev.abgeo.bugsnitch.model.Bug;
import dev.abgeo.bugsnitch.model.Comment;
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
     * @param bug New Bug entity.
     */
    public void bugCreated(Bug bug) {
        messagingTemplate.convertAndSend("/topic/bug/new", bug);
    }

    /**
     * Publish deleted Bug entity.
     *
     * @param bug Deleted Bug entity.
     */
    public void bugDeleted(Bug bug) {
        System.out.println("bugDeleted");
        messagingTemplate.convertAndSend("/topic/bug/delete", bug);
    }

    /**
     * Publish updated Bug entity.
     *
     * @param bug Updated Bug entity.
     */
    public void bugUpdated(Bug bug) {
        System.out.println("bugUpdated");
        messagingTemplate.convertAndSend("/topic/bug/" + bug.getId(), bug);
    }

    /**
     * Publish new Comment entity.
     *
     * @param comment New Comment entity.
     */
    public void commentCreated(Comment comment) {
        messagingTemplate.convertAndSend("/topic/bug/" + comment.getBug().getId() + "/comment", comment);
    }

    /**
     * Publish updated Comment entity.
     *
     * @param comment Updated Comment entity.
     */
    public void commentUpdated(Comment comment) {
        messagingTemplate.convertAndSend("/topic/comment/" + comment.getId(), comment);
    }

    /**
     * Publish deleted Comment entity.
     *
     * @param comment Deleted Comment entity.
     */
    public void commentDeleted(Comment comment) {
        messagingTemplate.convertAndSend("/topic/bug/" + comment.getBug().getId() + "/comment/deleted", comment);
    }

}
