package dev.abgeo.bugsnitch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.abgeo.bugsnitch.type.Status;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "bug_status_history")
public class BugStatusHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnore
    private Bug bug;

    @CreationTimestamp
    private LocalDateTime statusUpdatedAt;

    @Enumerated
    private Status oldStatus = Status.OPEN;

    private Status newStatus = Status.REJECTED;

    public Long getId() {
        return id;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    public LocalDateTime getStatusUpdatedAt() {
        return statusUpdatedAt;
    }

    public Status getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Status oldStatus) {
        this.oldStatus = oldStatus;
    }

    public Status getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Status newStatus) {
        this.newStatus = newStatus;
    }

}
