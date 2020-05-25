package dev.abgeo.bugsnitch.eventListener;

import dev.abgeo.bugsnitch.event.BugCreatedEvent;
import dev.abgeo.bugsnitch.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Listens to BugCreatedEvent.
 *
 * @author Temuri Takalandze
 */
@Component
public class BugCreatedEventListener implements ApplicationListener<BugCreatedEvent> {

    /**
     * WebSocketService dependency.
     */
    private final WebSocketService webSocketService;

    @Autowired
    public BugCreatedEventListener(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @Override
    public void onApplicationEvent(BugCreatedEvent event) {
        webSocketService.bugCreated(event.getBug());
    }

}
