package org.example.taller2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="exercises_routines")
public class ExerciseRoutine {
    @EmbeddedId
    private ExerciseRoutineId exerciseRoutineId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("routine_id")
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("exercise_id")
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;


}
