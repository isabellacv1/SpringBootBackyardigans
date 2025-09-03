package org.example.taller2.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="routines")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;

    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<UserRoutine> userRoutineList;


    public Routine() {}

    public Routine(Integer id,Date date) {
        this.id = id;
        this.date = date;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<UserRoutine> getUserRoutineList() {
        return userRoutineList;
    }

    public void setUserRoutineList(List<UserRoutine> userRoutineList) {
        this.userRoutineList = userRoutineList;
    }
}
