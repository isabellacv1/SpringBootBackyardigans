package org.example.taller2.entity;

import jakarta.persistence.*;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRoleId implements Serializable {

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "role_id")
    private Long roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof UserRoleId){
            UserRoleId that = (UserRoleId) o;
            return Objects.equals(userId, that.userId) && Objects.equals(roleId, that.roleId);
        }else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }

}
