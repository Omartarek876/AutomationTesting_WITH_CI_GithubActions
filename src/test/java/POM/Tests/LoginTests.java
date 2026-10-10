package POM.Tests;

import POM.pages.*;
import io.qameta.allure.*;
import org.testng.annotations.*;
import utils.DriverFactory;
import utils.JsonFileManager;

@Listeners(utils.TestNgListener.class)
@Feature("Login Feature")
public class LoginTests {
    JsonFileManager jsonData;

    @Test(description = "Login with valid email and password")
    @Description("TC02 - Login with valid email and password")
    @Severity(SeverityLevel.CRITICAL)
    public void TC02_LoginWithCorrectEmailAndPassword() {
        new LoginPage()
                .userLogin(jsonData.getTestData("$.validLogin.email"), jsonData.getTestData("$.validLogin.password"));
        new HomePage().
                assertOnExpectedLoggedInUser(jsonData.getTestData("$.validLogin.userName"));
    }

    @Test(description = "Login with incorrect email and password")
    @Description("TC03 - Login with invalid email and password")
    public void TC03_LoginWithIncorrectEmailAndPassword() {
        new LoginPage()
                .userLogin(jsonData.getTestData("$.invalidLogin.email"), jsonData.getTestData("$.invalidLogin.password"))
                .assertOnInvalidLoginErrorMessage("Your email or password is incorrect!");
    }

    @BeforeClass
    public void setup() {
        jsonData = new JsonFileManager("src/test/resources/loginTestData.json");
    }


    @BeforeMethod
    public void beforeMethod() {
        DriverFactory.InitializeDriver();
        new HomePage()
                .navigate(System.getProperty("baseUrl"))
                .assertOnPageTitles("Automation", "Full-Fledged practice website for Automation Engineers")
                .clickOnLoginButton();
        new LoginPage()
                .assertOnLoginFormTitle("Login to your account");
    }

    @AfterMethod
    public void afterMethod() {
        DriverFactory.quitDriver();
    }
}
