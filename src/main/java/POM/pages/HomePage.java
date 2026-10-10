package POM.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.BrowserUtils;
import utils.ElementActions;

public class HomePage {
    private By pageFirstTitle = By.xpath("//h1//span");
    private By pageSecondTitle = By.xpath("//div[@class='col-sm-6']//h2");
    private By signupButton = By.xpath("//i[@class='fa fa-lock']");
    private By loginButton = By.xpath("//i[@class='fa fa-lock']");
    private By deleteAccountButton = By.xpath("//i[@class='fa fa-trash-o']");
    ////i[@class = 'fa fa-user']/parent::a/b
    private By loggedInUser = By.xpath("//a[.//i[contains(@class,'fa-user')] ]/b");
    private By accountCreatedTitle = By.xpath("//h2[@data-qa='account-created']");
    private By accountDeletedTitle = By.xpath("//h2[@data-qa='account-deleted']");
    private By continueButton = By.xpath("//a[@data-qa='continue-button']");

    public HomePage navigate(String baseUrl) {
        BrowserUtils.navigateToUrl(baseUrl);
        return this;
    }

    public HomePage clickOnSignupButton() {
        ElementActions.clickElement(signupButton);
        return this;
    }

    public HomePage clickOnLoginButton() {
        ElementActions.clickElement(loginButton);
        return this;
    }

    public HomePage clickOnContinueButton() {
        ElementActions.clickElement(continueButton);
        return this;
    }

    public HomePage clickOnDeleteAccountButton() {
        ElementActions.clickElement(deleteAccountButton);
        return this;
    }

    public HomePage assertOnPageTitles(String title1, String title2) {
        Assert.assertEquals(ElementActions.getText(pageFirstTitle), title1);
        Assert.assertEquals(ElementActions.getText(pageSecondTitle), title2);
        return this;
    }

    public HomePage assertOnAccountCreatedTitle(String createdTitle) {
        Assert.assertEquals(ElementActions.getText(accountCreatedTitle), createdTitle);
        return this;
    }

    public HomePage assertOnAccountDeletedTitle(String  deletedTitle) {
        Assert.assertEquals(ElementActions.getText(accountDeletedTitle), deletedTitle);
        return this;
    }

    @Step ("verify logged in user as [{expectedLoggedInUser}]")
    public HomePage assertOnExpectedLoggedInUser(String expectedLoggedInUser) {
        Assert.assertEquals(expectedLoggedInUser,ElementActions.getText(loggedInUser));
        return this;
    }

}
