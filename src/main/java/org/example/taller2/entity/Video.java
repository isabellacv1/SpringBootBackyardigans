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
@Table(name="videos")
public class Video {

    @Id
    private Long id;
    private String url;

    @OneToOne(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private Exercise exercise;

}
