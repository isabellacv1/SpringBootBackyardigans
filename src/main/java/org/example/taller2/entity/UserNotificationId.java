package org.example.taller2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class UserNotificationId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "notification_id")
    private Long notificationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof UserNotificationId){
            UserNotificationId that = (UserNotificationId) o;
            return Objects.equals(userId, that.userId) && Objects.equals(notificationId, that.notificationId);
        }else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, notificationId);
    }

}
