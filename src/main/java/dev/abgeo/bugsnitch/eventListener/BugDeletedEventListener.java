package dev.abgeo.bugsnitch.eventListener;

import dev.abgeo.bugsnitch.event.BugDeletedEvent;
import dev.abgeo.bugsnitch.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Listens to BugDeletedEvent.
 *
 * @author Temuri Takalandze
 */
@Component
public class BugDeletedEventListener implements ApplicationListener<BugDeletedEvent> {

    /**
     * WebSocketService dependency.
     */
    private final WebSocketService webSocketService;

    @Autowired
    public BugDeletedEventListener(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @Override
    public void onApplicationEvent(BugDeletedEvent event) {
        webSocketService.bugDeleted(event.getBug());
    }

}
