package dev.abgeo.bugsnitch.eventListener;

import dev.abgeo.bugsnitch.event.CommentDeletedEvent;
import dev.abgeo.bugsnitch.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Listens to CommentDeletedEvent.
 *
 * @author Temuri Takalandze
 */
@Component
public class CommentDeletedEventListener implements ApplicationListener<CommentDeletedEvent> {

    /**
     * WebSocketService dependency.
     */
    private final WebSocketService webSocketService;

    @Autowired
    public CommentDeletedEventListener(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @Override
    public void onApplicationEvent(CommentDeletedEvent event) {
        webSocketService.commentDeleted(event.getComment());
    }

}
