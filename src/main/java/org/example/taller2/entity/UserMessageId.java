package org.example.taller2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
public class UserMessageId implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "message_id")
    private Integer messageId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof UserMessageId) {
            UserMessageId that = (UserMessageId) o;
            return Objects.equals(userId, that.userId) && Objects.equals(messageId, that.messageId);
        }else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, messageId);
    }

}
