package models;

import lombok.Data;

@Data
public class CreateUpdateUserRequestModel {
    private String name;
    private String job;
}