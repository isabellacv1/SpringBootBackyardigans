package org.example.taller2.entity;

import jakarta.persistence.Column;
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
public class ExerciseRoutineId implements Serializable {
    @Column(name= "exercise_id")
    private Integer exerciseId;

    @Column(name = "routine_id")
    private Integer routineId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof ExerciseRoutineId){
            ExerciseRoutineId that = (ExerciseRoutineId) o;
            return Objects.equals(exerciseId, that.exerciseId) && Objects.equals(routineId, that.routineId);
        }else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, routineId);
    }

}
