package dev.abgeo.bugsnitch.eventListener;

import dev.abgeo.bugsnitch.eventPublisher.CommentCreatedEventPublisher;
import dev.abgeo.bugsnitch.eventPublisher.CommentDeletedEventPublisher;
import dev.abgeo.bugsnitch.eventPublisher.CommentUpdatedEventPublisher;
import dev.abgeo.bugsnitch.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

/**
 * Defines Comment Entity Lifecycle events.
 *
 * @author Temuri Takalandze
 */
@Component
public class CommentEntityListener {

    /**
     * CommentCreatedEventPublisher dependency.
     */
    private CommentCreatedEventPublisher commentCreatedEventPublisher;

    /**
     * CommentUpdatedEventPublisher dependency.
     */
    private CommentUpdatedEventPublisher commentUpdatedEventPublisher;

    /**
     * CommentDeletedEventPublisher dependency.
     */
    private CommentDeletedEventPublisher commentDeletedEventPublisher;

    @Autowired
    public void setBugUpdatedEventPublisher(
            CommentCreatedEventPublisher commentCreatedEventPublisher,
            CommentUpdatedEventPublisher commentUpdatedEventPublisher,
            CommentDeletedEventPublisher commentDeletedEventPublisher
    ) {
        this.commentCreatedEventPublisher = commentCreatedEventPublisher;
        this.commentUpdatedEventPublisher = commentUpdatedEventPublisher;
        this.commentDeletedEventPublisher = commentDeletedEventPublisher;
    }

    @PostPersist
    void postPersist(Comment comment) {
        commentCreatedEventPublisher.publish(comment);
    }

    @PostUpdate
    void postUpdate(Comment comment) {
        commentUpdatedEventPublisher.publish(comment);
    }

    @PostRemove
    void postRemove(Comment comment) {
        commentDeletedEventPublisher.publish(comment);
    }

}
