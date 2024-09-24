package utils;

import com.github.javafaker.Faker;
import models.CreateUpdateUserRequestModel;
import models.RegisterUserRequestModel;

public class TestData {

    public static CreateUpdateUserRequestModel getUserData() {

        Faker faker = new Faker();

        CreateUpdateUserRequestModel userModel = new CreateUpdateUserRequestModel();
        userModel.setName(faker.name().firstName());
        userModel.setJob(faker.job().position());

        return userModel;
    }

    public static RegisterUserRequestModel getDataForRegistration() {

        Faker faker = new Faker();

        RegisterUserRequestModel userRegisterModel = new RegisterUserRequestModel();
        userRegisterModel.setPassword(faker.internet().password());

        return userRegisterModel;
    }

}