package org.example.taller2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class UserRoutineId implements Serializable {
    @Column(name="user_id")
    private Integer userId;

    @Column(name="routine_id")
    private Integer routineId;

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
