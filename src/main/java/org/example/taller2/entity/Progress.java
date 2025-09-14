package org.example.taller2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="progress")
public class Progress {

    @Id
    private Long id;
    private Integer repetitions;
    private Integer time;
    private Date date;


    @JsonIgnore
    @OneToMany(mappedBy = "progress", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommendation> recommendations;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("exerciseRoutineId")
    @JoinColumn(name = "exercise_routine_id")
    private ExerciseRoutine exerciseRoutine;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("effortId")
    @JoinColumn(name = "effort_id")
    private Effort effort;




}
