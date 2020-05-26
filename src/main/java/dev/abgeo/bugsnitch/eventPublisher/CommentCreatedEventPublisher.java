package dev.abgeo.bugsnitch.eventPublisher;

import dev.abgeo.bugsnitch.event.CommentCreatedEvent;
import dev.abgeo.bugsnitch.model.Comment;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * Publish CommentCreatedEvent.
 *
 * @author Temuri Takalandze
 */
@Component
public class CommentCreatedEventPublisher implements ApplicationEventPublisherAware {

    /**
     * ApplicationEventPublisher dependency.
     */
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Publish CommentCreatedEvent.
     *
     * @param comment Comment entity.
     */
    public void publish(final Comment comment) {
        applicationEventPublisher.publishEvent(new CommentCreatedEvent(this, comment));
    }

}
