package models;

import lombok.Data;

@Data
public class RegisterUserPayloadModel {
    private String email;
    private String password;
}