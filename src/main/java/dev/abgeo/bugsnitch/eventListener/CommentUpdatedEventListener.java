package dev.abgeo.bugsnitch.eventListener;

import dev.abgeo.bugsnitch.event.CommentUpdatedEvent;
import dev.abgeo.bugsnitch.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Listens to CommentUpdatedEvent.
 *
 * @author Temuri Takalandze
 */
@Component
public class CommentUpdatedEventListener implements ApplicationListener<CommentUpdatedEvent> {

    /**
     * WebSocketService dependency.
     */
    private final WebSocketService webSocketService;

    @Autowired
    public CommentUpdatedEventListener(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @Override
    public void onApplicationEvent(CommentUpdatedEvent event) {
        webSocketService.commentUpdated(event.getComment());
    }

}
