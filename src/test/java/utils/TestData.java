package utils;

import com.github.javafaker.Faker;

public class TestData {

    // userId is hardcoded because the API does not support full-value CRUD operations.
   public int userId = 2;

   // This is the hardcoded usedId for the user which does not exist in the system.
    public int unexistedUserId = 23;

    Faker faker = new Faker();
    public String name = faker.name().firstName();
    public String job = faker.job().position();
    public String password = faker.internet().password();

}