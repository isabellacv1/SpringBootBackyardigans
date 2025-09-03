package org.example.taller2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users_routines")
public class UserRoutine {
    @EmbeddedId
    private UserRoutineId userRoutineId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("routineId")
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    public UserRoutineId getUserRoutineId() {
        return userRoutineId;
    }

    public void setUserRoutineId(UserRoutineId userRoutineId) {
        this.userRoutineId = userRoutineId;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
