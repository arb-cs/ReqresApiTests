package models;

import lombok.Data;

@Data
public class RegisterUserRequestModel {
    private String email;
    private String password;
}