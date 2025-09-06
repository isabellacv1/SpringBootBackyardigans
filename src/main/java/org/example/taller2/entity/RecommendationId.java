package org.example.taller2.entity;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class RecommendationId implements Serializable {
    @Column(name = "studentId")
    private Long studentId;

    @Column(name = "notification_id")
    private Long trainerId;

    @Column(name = "progress_id")
    private Long progressId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof RecommendationId){
            RecommendationId that = (RecommendationId) o;
            return Objects.equals(studentId, that.studentId) && Objects.equals(trainerId, that.trainerId) && Objects.equals(progressId, that.progressId);
        }else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, trainerId, progressId);
    }

}
