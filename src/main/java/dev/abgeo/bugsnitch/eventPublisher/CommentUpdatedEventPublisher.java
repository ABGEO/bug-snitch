package dev.abgeo.bugsnitch.eventPublisher;

import dev.abgeo.bugsnitch.event.CommentUpdatedEvent;
import dev.abgeo.bugsnitch.model.Comment;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * Publish CommentUpdatedEvent.
 *
 * @author Temuri Takalandze
 */
@Component
public class CommentUpdatedEventPublisher implements ApplicationEventPublisherAware {

    /**
     * ApplicationEventPublisher dependency.
     */
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Publish CommentUpdatedEvent.
     *
     * @param comment Comment entity.
     */
    public void publish(final Comment comment) {
        applicationEventPublisher.publishEvent(new CommentUpdatedEvent(this, comment));
    }

}
