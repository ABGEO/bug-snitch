package dev.abgeo.bugsnitch.eventListener;

import dev.abgeo.bugsnitch.event.BugUpdatedEvent;
import dev.abgeo.bugsnitch.model.Bug;
import dev.abgeo.bugsnitch.model.BugStatusHistory;
import dev.abgeo.bugsnitch.repository.BugRepository;
import dev.abgeo.bugsnitch.repository.BugStatusHistoryRepository;
import dev.abgeo.bugsnitch.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Listens to BugUpdatedEvent.
 *
 * @author Temuri Takalandze
 */
@Component
public class BugUpdatedEventListener implements ApplicationListener<BugUpdatedEvent> {

    /**
     * Bug Repository dependency.
     */
    private final BugRepository bugRepository;

    /**
     * Bug Status History Repository dependency.
     */
    private final BugStatusHistoryRepository bugStatusHistoryRepository;

    /**
     * WebSocketService dependency.
     */
    private final WebSocketService webSocketService;

    @Autowired
    public BugUpdatedEventListener(
            BugRepository bugRepository,
            BugStatusHistoryRepository bugStatusHistoryRepository,
            WebSocketService webSocketService
    ) {
        this.bugStatusHistoryRepository = bugStatusHistoryRepository;
        this.bugRepository = bugRepository;
        this.webSocketService = webSocketService;
    }

    @Override
    public void onApplicationEvent(BugUpdatedEvent event) {
        createChangeHistoryEntry(event.getBug());
        webSocketService.bugUpdated(event.getBug());
    }

    /**
     * If bug status was changed, create BugStatusHistory entity.
     *
     * @param bug Updated Bug entity.
     */
    private void createChangeHistoryEntry(Bug bug) {
        bugRepository.findById(bug.getId())
                .map(b -> {
                    if (!b.getStatus().equals(b.getSavedState().getStatus())) {
                        BugStatusHistory history = new BugStatusHistory();
                        history.setBug(b);
                        history.setOldStatus(b.getSavedState().getStatus());
                        history.setNewStatus(b.getStatus());

                        bugStatusHistoryRepository.save(history);
                    }

                    return b;
                });
    }

}
