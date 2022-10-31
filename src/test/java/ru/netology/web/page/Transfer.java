package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.Thread.sleep;

public class Transfer {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement amountButton = $("[data-test-id=action-transfer]");
    private SelenideElement errorMessage = $("[data-test-id='error-message']");



    public DashboardPage validTransfer(String sum, String cardNum) {
        amountField.setValue(sum);
        fromField.sendKeys(cardNum);
        amountButton.click();
        return new DashboardPage();
    }

    public void findErrorMessage(String expectedText){
        errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
