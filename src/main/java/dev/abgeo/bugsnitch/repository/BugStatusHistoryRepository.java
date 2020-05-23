package dev.abgeo.bugsnitch.repository;

import dev.abgeo.bugsnitch.model.BugStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugStatusHistoryRepository extends JpaRepository<BugStatusHistory, Long> {
}
