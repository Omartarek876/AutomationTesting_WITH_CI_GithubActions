package POM.Tests;

import io.qameta.allure.*;
import org.testng.annotations.*;
import utils.*;
import POM.pages.*;

@Listeners(utils.TestNgListener.class)
@Feature("Register Feature")
public class RegisterTests {
    HomePage homePage;
    RegisterUserPage registerUserPage;
    JsonFileManager jsonData;

    @Test
    public void TC01_registerUser() {
        registerUserPage.sighupUser(jsonData.getTestData("$.userData.userName"), jsonData.getTestData("$.userData.email"))
                         .assertOnSignupFormTitle2("ENTER ACCOUNT INFORMATION")
                         .enterAccountInformation(
                jsonData.getTestData("$.userData.title"),
                jsonData.getTestData("$.userData.password"),
                jsonData.getTestData("$.userData.day"),
                jsonData.getTestData("$.userData.month"),
                jsonData.getTestData("$.userData.year"),
                Boolean.parseBoolean(jsonData.getTestData("$.userData.newsletter")),
                Boolean.parseBoolean(jsonData.getTestData("$.userData.specialOffers"))
        );

        registerUserPage.enterAddressInformation(
                jsonData.getTestData("$.userData.firstName"),
                jsonData.getTestData("$.userData.lastName"),
                jsonData.getTestData("$.userData.company"),
                jsonData.getTestData("$.userData.address1"),
                jsonData.getTestData("$.userData.address2"),
                jsonData.getTestData("$.userData.country"),
                jsonData.getTestData("$.userData.state"),
                jsonData.getTestData("$.userData.city"),
                jsonData.getTestData("$.userData.zipcode"),
                jsonData.getTestData("$.userData.mobileNumber")
        );
        homePage
                .assertOnAccountCreatedTitle("ACCOUNT CREATED!")
                .clickOnContinueButton()
                .assertOnExpectedLoggedInUser(jsonData.getTestData("$.userData.userName"))
                .clickOnDeleteAccountButton()
                .assertOnAccountDeletedTitle("ACCOUNT DELETED!")
                .clickOnContinueButton();
    }

    @Test
    public void TC05_RegisterUserWithExistingEmail() {
        registerUserPage.sighupUser(jsonData.getTestData("$.existingUserData.userName"), jsonData.getTestData("$.existingUserData.email"))
                        .assertOnExistingAccountMessage("Email Address already exist!");
    }

    @BeforeClass
    public void setup(){
        jsonData = new JsonFileManager("src/test/resources/RegistrationData.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        DriverFactory.InitializeDriver();
        homePage = new HomePage();
        registerUserPage = new RegisterUserPage();
        homePage.navigate(System.getProperty("baseUrl"));
        homePage.assertOnPageTitles("Automation", "Full-Fledged practice website for Automation Engineers");
        homePage.clickOnSignupButton();
        registerUserPage.assertOnSignupFormTitle1("New User Signup!");
    }

    @AfterMethod
    public void afterMethod() {
        DriverFactory.quitDriver();
    }

    @AfterClass
    public void tearDown(){
        System.out.println("Tear down...");
    }
}
