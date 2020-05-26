package dev.abgeo.bugsnitch.eventListener;

import dev.abgeo.bugsnitch.event.CommentCreatedEvent;
import dev.abgeo.bugsnitch.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Listens to CommentCreatedEvent.
 *
 * @author Temuri Takalandze
 */
@Component
public class CommentCreatedEventListener implements ApplicationListener<CommentCreatedEvent> {

    /**
     * WebSocketService dependency.
     */
    private final WebSocketService webSocketService;

    @Autowired
    public CommentCreatedEventListener(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @Override
    public void onApplicationEvent(CommentCreatedEvent event) {
        webSocketService.commentCreated(event.getComment());
    }

}
