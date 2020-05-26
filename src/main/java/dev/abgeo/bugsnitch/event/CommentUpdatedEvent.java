package dev.abgeo.bugsnitch.event;

import dev.abgeo.bugsnitch.model.Comment;
import org.springframework.context.ApplicationEvent;

/**
 * Contains Comment Updated Event.
 *
 * @author Temuri Takalandze
 */
public class CommentUpdatedEvent extends ApplicationEvent {

    /**
     * Attached Comment entity.
     */
    private final Comment comment;

    /**
     * {@inheritDoc}
     */
    public CommentUpdatedEvent(Object source, Comment comment) {
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
