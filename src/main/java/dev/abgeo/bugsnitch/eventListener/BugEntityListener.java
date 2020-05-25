package dev.abgeo.bugsnitch.eventListener;

import dev.abgeo.bugsnitch.eventPublisher.BugCreatedEventPublisher;
import dev.abgeo.bugsnitch.eventPublisher.BugDeletedEventPublisher;
import dev.abgeo.bugsnitch.eventPublisher.BugUpdatedEventPublisher;
import dev.abgeo.bugsnitch.model.Bug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PreUpdate;

/**
 * Defines Bug Entity Lifecycle events.
 *
 * @author Temuri Takalandze
 */
@Component
public class BugEntityListener {

    /**
     * BugCreatedEventPublisher dependency.
     */
    private BugCreatedEventPublisher bugCreatedEventPublisher;

    /**
     * BugUpdatedEventPublisher dependency.
     */
    private BugUpdatedEventPublisher bugUpdatedEventPublisher;

    /**
     * BugDeletedEventPublisher dependency.
     */
    private BugDeletedEventPublisher bugDeletedEventPublisher;

    @Autowired
    public void setBugUpdatedEventPublisher(
            BugCreatedEventPublisher bugCreatedEventPublisher,
            BugUpdatedEventPublisher bugUpdatedEventPublisher,
            BugDeletedEventPublisher bugDeletedEventPublisher
    ) {
        this.bugCreatedEventPublisher = bugCreatedEventPublisher;
        this.bugUpdatedEventPublisher = bugUpdatedEventPublisher;
        this.bugDeletedEventPublisher = bugDeletedEventPublisher;
    }

    @PostPersist
    void postPersist(Bug bug) {
        bugCreatedEventPublisher.publish(bug);
    }

    @PostLoad
    void postLoad(Bug bug) {
        bug.saveState();
    }

    @PreUpdate
    void preUpdate(Bug bug) {
        bugUpdatedEventPublisher.publish(bug);
    }

    @PostRemove
    void postRemove(Bug bug) {
        bugDeletedEventPublisher.publish(bug);
    }

}
