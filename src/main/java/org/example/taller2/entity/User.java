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
@Getter
@Setter
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserNotification> userNotifications;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserMessage> userMessages;

    @JsonIgnore
    @OneToMany(mappedBy = "studentUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainerStudent> trainerStudentsAsStudent;

    @JsonIgnore
    @OneToMany(mappedBy = "trainerUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainerStudent> trainerStudentsAsTrainer;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventParticipation> eventParticipations;

    @JsonIgnore
    @OneToMany(mappedBy = "trainerUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommendation> trainerRecommendations;

    @JsonIgnore
    @OneToMany(mappedBy = "studentUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommendation> studentRecommendations;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }
}
