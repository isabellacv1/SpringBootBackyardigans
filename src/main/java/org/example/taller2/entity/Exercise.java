package org.example.taller2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String duration;
    private String difficulty;
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseRoutine> exerciseRoutine;

    @OneToOne(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private Video video;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("excerciseTypeId")
    @JoinColumn(name = "exerciseType_id")
    private ExerciseType exerciseType;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dificultyId")
    @JoinColumn(name = "difficulty_id")
    private Difficulty exerciseDifficulty;



}
