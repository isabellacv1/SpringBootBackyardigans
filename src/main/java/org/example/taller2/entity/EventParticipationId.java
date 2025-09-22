package org.example.taller2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class EventParticipationId implements Serializable {

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "event_id")
    private Long eventId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof EventParticipationId) {
            EventParticipationId that = (EventParticipationId) o;
            return Objects.equals(studentId, that.studentId) && Objects.equals(eventId, that.eventId);
        }else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, eventId);
    }
}
