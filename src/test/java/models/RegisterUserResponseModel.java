package models;

import lombok.Data;

@Data
public class RegisterUserResponseModel {
    private int id;
    private String token;
}