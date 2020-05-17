package dev.abgeo.bugsnitch.controller;

import dev.abgeo.bugsnitch.model.Bug;
import dev.abgeo.bugsnitch.repository.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CRUD Controller for Bug entity.
 *
 * @author Temuri Takalandze
 */
@RestController
public class BugController {

    BugRepository bugRepository;

    @Autowired
    public BugController(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    /**
     * Create new Bug.
     *
     * @param bug JSON with Bug content.
     *
     * @return Created entity.
     */
    @PostMapping("/bug")
    public ResponseEntity<Bug> create(@RequestBody Bug bug) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bugRepository.save(bug));
    }

    /**
     * Get all Bugs.
     *
     * @return List og Bugs.
     */
    @GetMapping("/bug")
    public List<Bug> getAll() {
        return bugRepository.findAll();
    }

    /**
     * Get single Bug by ID.
     *
     * @param id Bug ID.
     *
     * @return Bug entity if exists, or 404 with empty body.
     */
    @GetMapping("/bug/{id}")
    public ResponseEntity<Bug> getSingle(@PathVariable Long id) {
        return bugRepository.findById(id)
                .map(bug -> ResponseEntity.ok().body(bug))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    /**
     * Update Bug.
     *
     * @param bug JSON with Bug content.
     * @param id  Bug ID.
     *
     * @return Updated Bug entity if exists, or 404 with empty body.
     */
    @PatchMapping("/bug/{id}")
    public ResponseEntity<Bug> update(@RequestBody Bug bug, @PathVariable Long id) {
        return bugRepository.findById(id)
                .map(b -> {
                    b.setTitle(bug.getTitle());
                    b.setBody(bug.getBody());
                    b.setPriority(bug.getPriority());
                    b.setStatus(bug.getStatus());

                    return ResponseEntity.ok().body(bugRepository.save(b));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Delete Bug.
     *
     * @param id Bug ID.
     *
     * @return 200 with empty body if deleted, or 404.
     */
    @DeleteMapping("/bug/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return bugRepository.findById(id)
                .map(bug -> {
                    bugRepository.delete(bug);
                    return ResponseEntity.status(HttpStatus.OK).build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
