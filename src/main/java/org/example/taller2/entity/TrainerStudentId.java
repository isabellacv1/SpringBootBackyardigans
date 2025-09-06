package org.example.taller2.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class TrainerStudentId implements Serializable {

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "trainer_id")
    private Integer trainerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof TrainerStudentId) {
            TrainerStudentId that = (TrainerStudentId) o;
            return Objects.equals(studentId, that.studentId) && Objects.equals(trainerId, that.trainerId);
        }else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, trainerId);
    }
}
