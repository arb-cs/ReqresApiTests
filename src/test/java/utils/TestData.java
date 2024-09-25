package utils;

import com.github.javafaker.Faker;
import models.CreateUpdateUserRequestModel;
import models.RegisterUserRequestModel;

public class TestData {

    private final static Faker faker = new Faker();

    public static CreateUpdateUserRequestModel getUserData() {

        CreateUpdateUserRequestModel userModel = new CreateUpdateUserRequestModel();
        userModel.setName(faker.name().firstName());
        userModel.setJob(faker.job().position());

        return userModel;
    }

    public static RegisterUserRequestModel getDataForRegistration() {

        RegisterUserRequestModel userRegisterModel = new RegisterUserRequestModel();
        // Value for the email is hardcoded because the API does not allow other credentials
        userRegisterModel.setEmail("eve.holt@reqres.in");
        userRegisterModel.setPassword(faker.internet().password());

        return userRegisterModel;
    }

}