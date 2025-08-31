package org.example.taller2.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date endDate;
    private String place;
    private String type;
    private String placeCapacity;

    public Event() {}

    public Event(Long id, Date startDate, Date endDate, String place, String type, String placeCapacity) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.type = type;
        this.placeCapacity = placeCapacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlaceCapacity() {
        return placeCapacity;
    }

    public void setPlaceCapacity(String placeCapacity) {
        this.placeCapacity = placeCapacity;
    }
}
