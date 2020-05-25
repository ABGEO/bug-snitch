package dev.abgeo.bugsnitch.event;

import dev.abgeo.bugsnitch.model.Bug;
import org.springframework.context.ApplicationEvent;

/**
 * Contains Bug Created Event.
 *
 * @author Temuri Takalandze
 */
public class BugCreatedEvent extends ApplicationEvent {

    /**
     * Attached Bug entity.
     */
    private final Bug bug;

    /**
     * {@inheritDoc}
     */
    public BugCreatedEvent(Object source, Bug bug) {
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
