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
@Table(name="progresses")
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer repetitions;
    private Integer time;
    private Date date;


    @JsonIgnore
    @OneToMany(mappedBy = "progress", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommendation> recommendations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "exercise_id", referencedColumnName = "exercise_id"),
            @JoinColumn(name = "routine_id", referencedColumnName = "routine_id")
    })
    private ExerciseRoutine exerciseRoutine;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "effort_id")
    private Effort effort;




}
