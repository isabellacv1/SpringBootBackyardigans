package org.example.taller2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRoutineId implements Serializable {
    @Column(name="user_id")
    private Integer userId;

    @Column(name="routine_id")
    private Integer routineId;

    public UserRoutineId() {}

    public UserRoutineId(Integer userId, Integer routineId) {
        this.userId = userId;
        this.routineId = routineId;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o){
        if  (this == o) return true;
        if (o instanceof UserRoutineId){
            UserRoutineId that  = (UserRoutineId) o;
            return Objects.equals(userId, that.userId) && Objects.equals(routineId, that.routineId);
        } else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, routineId);
    }

    }
