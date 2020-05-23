package dev.abgeo.bugsnitch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.abgeo.bugsnitch.eventListener.BugEntityListener;
import dev.abgeo.bugsnitch.type.Priority;
import dev.abgeo.bugsnitch.type.Status;
import org.apache.commons.lang3.SerializationUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "bug")
@EntityListeners(BugEntityListener.class)
public class Bug implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2048)
    private String body;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Enumerated
    private Priority priority = Priority.LOW;

    @Enumerated
    private Status status = Status.OPEN;

    @OneToMany(mappedBy = "bug")
    @Fetch(FetchMode.JOIN)
    @OrderBy("id DESC")
    private Set<BugStatusHistory> statusHistory;

    @Transient
    @JsonIgnore
    private transient Bug savedState;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<BugStatusHistory> getStatusHistory() {
        return statusHistory;
    }

    /**
     * Clone current state to <b>this.savedState</b>.
     */
    public void saveState() {
        this.savedState = SerializationUtils.clone(this);
    }

    public Bug getSavedState() {
        return savedState;
    }

}
