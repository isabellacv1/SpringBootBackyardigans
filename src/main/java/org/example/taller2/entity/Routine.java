package org.example.taller2.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="routines")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;


    @ManyToMany
    @JoinTable(
            name = "exercise_routine",
            joinColumns = @JoinColumn(name = "routine_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<Exercise> exercises = new ArrayList<>();

    @ManyToMany(mappedBy = "routines")
    private Set<User> users = new HashSet<>();


    public Routine() {}

    public Routine(Long id,Date date) {
        this.id = id;
        this.date = date;

    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}
    public List<Exercise> getExercises() {return exercises;}
    public void setExercises(List<Exercise> exercises) {this.exercises = exercises;}
    public Set<User> getUsers() {return users;}
    public void setUsers(Set<User> users) {this.users = users;}
}
