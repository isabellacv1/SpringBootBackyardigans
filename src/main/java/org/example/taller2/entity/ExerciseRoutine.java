package org.example.taller2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @MapsId("routineId")
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("exerciseId")
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @JsonIgnore
    @OneToMany(mappedBy = "exerciseRoutine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Progress> progresses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;


}
