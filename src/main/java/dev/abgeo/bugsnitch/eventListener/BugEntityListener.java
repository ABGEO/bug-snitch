package dev.abgeo.bugsnitch.eventListener;

import dev.abgeo.bugsnitch.eventPublisher.BugUpdatedEventPublisher;
import dev.abgeo.bugsnitch.model.Bug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostLoad;
import javax.persistence.PreUpdate;

/**
 * Defines Bug Entity Lifecycle events.
 *
 * @author Temuri Takalandze
 */
@Component
public class BugEntityListener {

    /**
     * BugUpdatedEventPublisher dependency.
     */
    private BugUpdatedEventPublisher bugUpdatedEventPublisher;

    @Autowired
    public void setBugUpdatedEventPublisher(BugUpdatedEventPublisher bugUpdatedEventPublisher) {
        this.bugUpdatedEventPublisher = bugUpdatedEventPublisher;
    }

    @PreUpdate
    void preUpdate(Bug bug) {
        bugUpdatedEventPublisher.publish(bug);
    }

    @PostLoad
    void postLoad(Bug bug) {
        bug.saveState();
    }

}
