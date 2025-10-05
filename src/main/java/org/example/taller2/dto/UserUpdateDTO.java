package org.example.taller2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    private String name;
    private String newEmail;
    private String newPassword;
    private String newRoleName;

}