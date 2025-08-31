package org.example.taller2.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "exercises")
    private List<Routine> routines = new ArrayList<>();

    public Exercise() {}

    public Exercise(String name, String description, String duration, String difficulty, String type) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.difficulty = difficulty;
        this.type = type;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public List<Routine> getRoutines() { return routines; }
    public void setRoutines(List<Routine> routines) { this.routines = routines; }

}
