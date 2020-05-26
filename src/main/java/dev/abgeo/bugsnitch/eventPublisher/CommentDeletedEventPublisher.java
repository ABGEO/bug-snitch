package dev.abgeo.bugsnitch.eventPublisher;

import dev.abgeo.bugsnitch.event.CommentDeletedEvent;
import dev.abgeo.bugsnitch.model.Comment;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * Publish CommentDeletedEvent.
 *
 * @author Temuri Takalandze
 */
@Component
public class CommentDeletedEventPublisher implements ApplicationEventPublisherAware {

    /**
     * ApplicationEventPublisher dependency.
     */
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Publish CommentDeletedEvent.
     *
     * @param comment Comment entity.
     */
    public void publish(final Comment comment) {
        applicationEventPublisher.publishEvent(new CommentDeletedEvent(this, comment));
    }

}
