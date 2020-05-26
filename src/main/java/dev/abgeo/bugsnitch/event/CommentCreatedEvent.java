package dev.abgeo.bugsnitch.event;

import dev.abgeo.bugsnitch.model.Comment;
import org.springframework.context.ApplicationEvent;

/**
 * Contains Comment Created Event.
 *
 * @author Temuri Takalandze
 */
public class CommentCreatedEvent extends ApplicationEvent {

    /**
     * Attached Comment entity.
     */
    private final Comment comment;

    /**
     * {@inheritDoc}
     */
    public CommentCreatedEvent(Object source, Comment comment) {
        super(source);
        this.comment = comment;
    }

    /**
     * Get Attached Comment.
     *
     * @return Attached Comment entity.
     */
    public Comment getComment() {
        return comment;
    }

}
