package models;

import lombok.Data;

@Data
public class CreateOrUpdateUserPayloadModel {
    private String name;
    private String job;
}