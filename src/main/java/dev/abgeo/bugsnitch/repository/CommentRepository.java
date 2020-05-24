package dev.abgeo.bugsnitch.repository;

import dev.abgeo.bugsnitch.model.Bug;
import dev.abgeo.bugsnitch.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBug(Bug bug);

}
