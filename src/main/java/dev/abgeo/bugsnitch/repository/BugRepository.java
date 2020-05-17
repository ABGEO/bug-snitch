package dev.abgeo.bugsnitch.repository;

import dev.abgeo.bugsnitch.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug, Long> {
}
