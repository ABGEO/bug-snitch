package dev.abgeo.bugsnitch.eventPublisher;

import dev.abgeo.bugsnitch.event.BugDeletedEvent;
import dev.abgeo.bugsnitch.model.Bug;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * Publish BugDeletedEvent.
 *
 * @author Temuri Takalandze
 */
@Component
public class BugDeletedEventPublisher implements ApplicationEventPublisherAware {

    /**
     * ApplicationEventPublisher dependency.
     */
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Publish BugDeletedEvent.
     *
     * @param bug Bug entity.
     */
    public void publish(final Bug bug) {
        applicationEventPublisher.publishEvent(new BugDeletedEvent(this, bug));
    }

}
