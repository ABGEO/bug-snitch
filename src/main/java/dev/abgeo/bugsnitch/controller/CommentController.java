package dev.abgeo.bugsnitch.controller;

import dev.abgeo.bugsnitch.model.Comment;
import dev.abgeo.bugsnitch.repository.BugRepository;
import dev.abgeo.bugsnitch.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * CRUD Controller for Comment entity.
 *
 * @author Temuri Takalandze
 */
@RestController
public class CommentController {

    /**
     * Bug Repository dependency.
     */
    BugRepository bugRepository;

    /**
     * Comment Repository dependency.
     */
    CommentRepository commentRepository;

    @Autowired
    public CommentController(BugRepository bugRepository, CommentRepository commentRepository) {
        this.bugRepository = bugRepository;
        this.commentRepository = commentRepository;
    }

    /**
     * Create new Comment for given Bug.
     *
     * @param bugId   ID of Bug to comment on.
     * @param comment JSON with Comment content.
     *
     * @return Created entity.
     */
    @Async
    @PostMapping("/bug/{bugId}/comment")
    public CompletableFuture<ResponseEntity<Comment>> create(@PathVariable("bugId") Long bugId, @RequestBody Comment comment) {
        return CompletableFuture.completedFuture(bugRepository.findById(bugId)
                .map(bug -> {
                    comment.setBug(bug);
                    return ResponseEntity.status(HttpStatus.CREATED).body(commentRepository.save(comment));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    /**
     * Get all Comments of given Bug.
     *
     * @param bugId Bug ID.
     *
     * @return List of Comments.
     */
    @Async
    @GetMapping("/bug/{bugId}/comment")
    public CompletableFuture<ResponseEntity<List<Comment>>> getAll(@PathVariable("bugId") Long bugId) {
        return CompletableFuture.completedFuture(bugRepository.findById(bugId)
                .map(bug -> ResponseEntity.status(HttpStatus.OK).body(commentRepository.findByBug(bug)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    /**
     * Update Comment.
     *
     * @param comment JSON with Comment content.
     * @param id      Comment ID.
     *
     * @return Updated Comment entity if exists, or 404 with empty body.
     */
    @Async
    @PatchMapping("/comment/{id}")
    public CompletableFuture<ResponseEntity<Comment>> update(@RequestBody Comment comment, @PathVariable Long id) {
        return CompletableFuture.completedFuture(commentRepository.findById(id)
                .map(c -> {
                    c.setBody(comment.getBody());

                    return ResponseEntity.ok().body(commentRepository.save(c));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    /**
     * Delete Comment.
     *
     * @param id Comment ID.
     *
     * @return 200 with empty body if deleted, or 404.
     */
    @Async
    @DeleteMapping("/comment/{id}")
    public CompletableFuture<ResponseEntity<?>> delete(@PathVariable Long id) {
        return CompletableFuture.completedFuture(commentRepository.findById(id)
                .map(comment -> {
                    commentRepository.delete(comment);
                    return ResponseEntity.status(HttpStatus.OK).build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

}
