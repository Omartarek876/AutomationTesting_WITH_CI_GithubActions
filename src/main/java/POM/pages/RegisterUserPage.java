package POM.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.ElementActions;

public class RegisterUserPage {

    private By signupFormTitle1 = By.xpath("//div[@class='signup-form']//h2");
    private By signupFormTitle2 = By.xpath("//div[@class='login-form']/h2[@class='title text-center']/b");
    private By userNameField = By.xpath("//input[@data-qa='signup-name']");
    private By emailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']");
    private By mrTitleRadio = By.id("id_gender1");
    private By mrsTitleRadio = By.id("id_gender2");
    private By passwordInput = By.id("password");
    private By daysSelect = By.id("days");
    private By monthsSelect = By.id("months");
    private By yearsSelect = By.id("years");
    private By newsletterCheckbox = By.id("newsletter");
    private By specialOffersCheckbox = By.id("optin");
    private By firstNameInput = By.id("first_name");
    private By lastNameInput = By.id("last_name");
    private By companyInput = By.id("company");
    private By address1Input = By.id("address1");
    private By address2Input = By.id("address2");
    private By countrySelect = By.id("country");
    private By stateInput = By.id("state");
    private By cityInput = By.id("city");
    private By zipcodeInput = By.id("zipcode");
    private By mobileNumberInput = By.id("mobile_number");
    private By createAccountBtn = By.xpath("//button[@data-qa='create-account']");
    private By invalidLoginErrorMessage = By.xpath("//p[@style='color: red;']");


    public RegisterUserPage sighupUser(String userName, String email) {
        ElementActions.typeText(userNameField, userName);
        ElementActions.typeText(emailField, email);
        ElementActions.clickElement(signupButton);
        return this;
    }

    public RegisterUserPage enterAccountInformation(String title, String password, String day,
                                        String month, String year, Boolean Newsletter, Boolean SpecialOffers) {
        selectUserTitle(title);
        ElementActions.typeText(passwordInput, password);
        selectDateOfBirth(day, month, year);
        checkNewsletter(Newsletter);
        checkSpecialOffers(SpecialOffers);
        return this;
    }

    public RegisterUserPage enterAddressInformation(String firstName, String lastName, String company, String address1, String address2,
                                        String country, String state, String city, String zipcode, String mobileNumber) {
        ElementActions.typeText(firstNameInput, firstName);
        ElementActions.typeText(lastNameInput, lastName);
        ElementActions.typeText(companyInput, company);
        ElementActions.typeText(address1Input, address1);
        ElementActions.typeText(address2Input, address2);
        ElementActions.typeText(countrySelect, country);
        ElementActions.typeText(stateInput, state);
        ElementActions.typeText(cityInput, city);
        ElementActions.typeText(zipcodeInput, zipcode);
        ElementActions.typeText(mobileNumberInput, mobileNumber);
        ElementActions.clickElement(createAccountBtn);
        return this;
    }

    private RegisterUserPage selectUserTitle(String title) {
        if (title.toLowerCase().trim().equals("mr")) {
            ElementActions.selectRadioButton(mrTitleRadio);
        } else if (title.toLowerCase().trim().equals("mrs")) {
            ElementActions.selectRadioButton(mrsTitleRadio);
        } else {
            System.out.println("Invalid Title");
        }
        return this;
    }

    private RegisterUserPage checkNewsletter(Boolean newsletter) {
        if (newsletter) {
            ElementActions.checkCheckbox(newsletterCheckbox);
        } else {
            ElementActions.uncheckCheckbox(newsletterCheckbox);
        }
        return this;
    }

    private RegisterUserPage checkSpecialOffers(Boolean specialOffers) {
        if (specialOffers) {
            ElementActions.checkCheckbox(specialOffersCheckbox);
        } else {
            ElementActions.uncheckCheckbox(specialOffersCheckbox);
        }
        return this;
    }

    private RegisterUserPage selectDateOfBirth(String day, String month, String year) {
        ElementActions.selectDropdownByVisibleText(daysSelect, day);
        ElementActions.selectDropdownByVisibleText(monthsSelect, month);
        ElementActions.selectDropdownByVisibleText(yearsSelect, year);
        return this;
    }

    @Step ("verify signup form title1 is [{signupTitle1}]")
    public RegisterUserPage assertOnSignupFormTitle1(String signupTitle1) {
        Assert.assertEquals(ElementActions.getText(signupFormTitle1), signupTitle1);
        return this;
    }

    @Step ("verify signup form title2 is [{signupTitle2}]")
    public RegisterUserPage assertOnSignupFormTitle2(String signupTitle2) {
        Assert.assertEquals(ElementActions.getText(signupFormTitle2), signupTitle2);
        return this;
    }

    @Step("verify that the user [{invalidLoginMessage}] is already exsited")
    public RegisterUserPage assertOnExistingAccountMessage(String invalidLoginMessage) {
        Assert.assertEquals(ElementActions.getText(invalidLoginErrorMessage), invalidLoginMessage);
        return this;
    }
}
