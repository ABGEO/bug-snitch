package dev.abgeo.bugsnitch.event;

import dev.abgeo.bugsnitch.model.Bug;
import org.springframework.context.ApplicationEvent;

/**
 * Contains Bug Updated Event.
 *
 * @author Temuri Takalandze
 */
public class BugUpdatedEvent extends ApplicationEvent {

    /**
     * Attached Bug entity.
     */
    private final Bug bug;

    /**
     * {@inheritDoc}
     */
    public BugUpdatedEvent(Object source, Bug bug) {
        super(source);
        this.bug = bug;
    }

    /**
     * Get Attached Bug.
     *
     * @return Attached Bug entity.
     */
    public Bug getBug() {
        return bug;
    }

}
