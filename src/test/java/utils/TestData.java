package utils;

import com.github.javafaker.Faker;

public class TestData {

    Faker faker = new Faker();
    public String name = faker.name().firstName();
    public String job = faker.job().position();
    public String password = faker.internet().password();

}